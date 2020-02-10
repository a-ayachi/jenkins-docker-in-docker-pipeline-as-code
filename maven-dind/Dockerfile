FROM ubuntu:18.04

RUN apt-get update -qq && apt-get install --no-install-recommends -qqy \
    apt-transport-https \
    ca-certificates \
    curl \
    lxc \
    iptables \
    openjdk-8-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*
    
# Install Docker from Docker Inc. repositories.
RUN curl -sSL https://get.docker.com/ | sh

# Install docker-compose we will need it on integration test
RUN curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \

 chmod +x /usr/local/bin/docker-compose && \

 echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

# Install the magic wrapper.
ADD ./wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

VOLUME /var/lib/docker
ENTRYPOINT ["wrapdocker"]


