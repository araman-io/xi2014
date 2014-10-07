INSTALL_DIR=/usr/bin/devtools;
echo "Downloading JDK 8 Binary";
pwd;
if [ ! -f /tmp/jdk-8u20-linux-x64.tar.gz ]
then
    echo "going to download the java binary";
    wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie"  http://download.oracle.com/otn-pub/java/jdk/8u20-b26/jdk-8u20-linux-x64.tar.gz -O /tmp/jdk-8u20-linux-x64.tar.gz
fi;


echo "Installing Maven Binary"
if [ ! -f /tmp/apache-maven-3.2.3-bin.tar.gz ]
then
    echo "going to download the maven binary";
    wget --no-check-certificate http://mirrors.ibiblio.org/apache/maven/maven-3/3.2.3/binaries/apache-maven-3.2.3-bin.tar.gz -O /tmp/apache-maven-3.2.3-bin.tar.gz
fi;

mkdir -p $INSTALL_DIR
cd $INSTALL_DIR

if [ ! -h $INSTALL_DIR/jdk8 ]
then
    echo "Installing and linking to $INSTALL_DIR/jdk8";
    tar -xvzf /tmp/jdk-8u20-linux-x64.tar.gz
    /usr/bin/devtools/jdk1.8.0_20/java -version
    ln -s $INSTALL_DIR/jdk1.8.0_20 $INSTALL_DIR/jdk8
    chmod 755 $INSTALL_DIR/jdk8
fi;

if [ ! -h $INSTALL_DIR/maven ]
then
    echo "Installing and linking to $INSTALL_DIR/maven";
    tar -xvzf /tmp/apache-maven-3.2.3-bin.tar.gz
    ln -s $INSTALL_DIR/apache-maven-3.2.3 $INSTALL_DIR/maven
    chmod 755 $INSTALL_DIR/maven
fi;

grep "JAVA_HOME" /etc/bash.bashrc
if [ $? -ne 0 ]
then
    echo "Adjusting path variables";
cat >> /etc/bash.bashrc << EOF
    # vagrant provisioned env variables on `date`
    MAVEN_HOME=$INSTALL_DIR/maven; 
    JAVA_HOME=$INSTALL_DIR/jdk8; 
    PATH=$PATH:$INSTALL_DIR/jdk8/bin:$INSTALL_DIR/maven/bin
    export PATH;
    export MAVEN_HOME; 
    export JAVA_HOME; 
EOF
fi;
