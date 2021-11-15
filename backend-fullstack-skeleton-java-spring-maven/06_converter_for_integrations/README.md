Linux Debian 10

    cat /etc/os-release | grep VERSION_CODENAME | cut -d = -f 2

Java 8 

    sudo apt update
    sudo apt install -y wget apt-transport-https gnupg
    wget https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --import public
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --export --output adoptopenjdk-archive-keyring.gpg
    rm adoptopenjdk-keyring.gpg
    sudo mv adoptopenjdk-archive-keyring.gpg /usr/share/keyrings 
    echo "deb [signed-by=/usr/share/keyrings/adoptopenjdk-archive-keyring.gpg] https://adoptopenjdk.jfrog.io/adoptopenjdk/deb buster main" | sudo tee /etc/apt/sources.list.d/adoptopenjdk.list
    sudo apt update
    sudo apt install adoptopenjdk-8-hotspot

    JAVA_HOME=/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64
    PATH=$PATH:$JAVA_HOME/bin
    export JRE_HOME


DataBase management system PostgreSQL 11.12

    sudo apt install -y postgresql
    sudo systemctl restart postgresql
    sudo -u postgres createdb USERNAME
    sudo -u postgres createuser USERNAME

    #Test PostgreSQL installation
    psql
    select (1+1);
    #Output 2

    exit

Note! Even though DataBase might use snake_convention after Hibernate handling do use camelCase with Java (Python uses snake_convention) because SpringData @Repository interface does not work with snake_convention

<br>

<hr>

<br>

This proof of concept ([POC](https://en.wikipedia.org/wiki/Proof_of_concept)) for FullStack skeleton is based on this [<i>Business Case</i>](https://web.archive.org/web/20210803084701/https://github.com/solita/vaccine-exercise-2021) exercise.

<br>

<hr>

<br>

Relational DataBase: [One-to-Many](https://techyowls.com/post/jpa-one-to-many)

Relational DataBase: [Many-to-Many](https://www.baeldung.com/jpa-many-to-many)


    #Confirm PostgreSQL relation @OneToMany
    psql
    SELECT * FROM public.orders;
    
    SELECT * FROM public.vaccinations;

    exit