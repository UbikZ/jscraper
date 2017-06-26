#!/bin/bash

set -e

invalidateIndex=0

# Enable CLI for CloudFront
aws configure set preview.cloudfront true

while read line || [ -n "$line" ];
do
    file=$(echo ${line} | awk '{print $1}')
    metadata=$(echo ${line} | awk '{print $2}')

    echo "Check file ${line}..."
    # Get current object
    set +e
    aws s3api head-object --bucket ${S3_BUCKET_NAME} --key ${file} > metadata
    set -e

    awsChecksum=$(node ./scripts/awsChecksum.js metadata)
    currChecksum=$(node ./scripts/currentChecksum.js ./static/${file})

    [ "$currChecksum" != "$awsChecksum" ] && echo "Need to update (${currChecksum} vs Aws ${awsChecksum})." || echo "Up to date. (${currChecksum})"

    if [ "$currChecksum" != "$awsChecksum" ]; then
        [ "${file}" == "index.html" ] && invalidateIndex=1
        echo "Pushing file ${file} to s3."
        # Push files
        aws s3 cp ./static/${file} s3://${S3_BUCKET_NAME}/${file} \
            --metadata md5chksum=${currChecksum},${metadata}

    fi
done < manifest

if [ ${invalidateIndex} -eq 1 ]; then
    echo "Invalidate CloudFront index.html cache."
    # Invalidate Cloud Front Cache for index.html
    aws cloudfront create-invalidation --distribution-id ${CF_DISTRIBUTION_ID} --paths "/index.html";
fi