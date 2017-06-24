#!/bin/bash

set -e

while read line;
do
    file=$(echo ${line} | awk '{print $1}')
    metadata=$(echo ${line} | awk '{print $2}')
    aws s3 cp ./static/${line} s3://${S3_BUCKET_NAME}/${line} --metadata "${metadata}"
done < manifest