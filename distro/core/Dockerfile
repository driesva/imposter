ARG BASE_IMAGE_TAG=latest

FROM outofcoffee/imposter-base:${BASE_IMAGE_TAG}

LABEL MAINTAINER="Pete Cornish <outofcoffee@gmail.com>"

CMD ["--plugin=openapi", "--plugin=rest", "--plugin=soap", "--configDir=/opt/imposter/config"]
