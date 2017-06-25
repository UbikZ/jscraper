#!/bin/bash

set -e

while read line;
do
    file=$(echo ${line} | awk '{print $1}')
    metadata=$(echo ${line} | awk '{print $2}')
    md5=$(openssl md5 -binary PATH/TO/FILE | base64)

    # Push files
    aws s3 cp ./static/${file} s3://${S3_BUCKET_NAME}/${file} \
        --metadata md5chksum=${md5},${metadata} \
        --content-md5 ${md5}

    # Invalidate Cloud Front Cache
    aws cloudfront create-invalidation --distribution-id ${CF_DISTRIBUTION_ID} --paths "/*";

done < manifest
