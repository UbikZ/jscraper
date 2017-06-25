#!/bin/bash

set -e

# Enable CLI for CloudFront
aws configure set preview.cloudfront true

while read line;
do
    file=$(echo ${line} | awk '{print $1}')
    metadata=$(echo ${line} | awk '{print $2}')

    # Get current object
    aws s3api head-object --bucket ${S3_BUCKET_NAME} --key ${file} > metadata

    awsChecksum=$(node ./scripts/awsChecksum.js metadata)
    currChecksum=$(node ./scripts/currentChecksum.js ./static/${file})

    echo "Current ${currChecksum} vs Aws ${awsChecksum}"
    [ "$currChecksum" != "$awsChecksum" ] && echo "Need to update." || echo "Up to date."

    if [ "$currChecksum" != "$awsChecksum" ]; then
        echo "Pushing file ${file} to s3"
        # Push files
        aws s3 cp ./static/${file} s3://${S3_BUCKET_NAME}/${file} \
            --metadata md5chksum=${currChecksum},${metadata}

        echo "Invalidate CloudFront cache for ${file}"
        # Invalidate Cloud Front Cache
        aws cloudfront create-invalidation --distribution-id ${CF_DISTRIBUTION_ID} --paths "/*";
    fi
done < manifest
