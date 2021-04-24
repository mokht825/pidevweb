<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210424134935 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE avis');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE gym');
        $this->addSql('DROP TABLE login');
        $this->addSql('DROP TABLE profile');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE typesport');
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY FK_3F596DCCD800119A');
        $this->addSql('DROP INDEX IDX_3F596DCCD800119A ON coach');
        $this->addSql('ALTER TABLE coach CHANGE typesesport typesesport_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT FK_3F596DCCD800119A FOREIGN KEY (typesesport_id) REFERENCES diplome (id)');
        $this->addSql('CREATE INDEX IDX_3F596DCCD800119A ON coach (typesesport_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE avis (Titre VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, TypeAvis VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, DateEvent VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, Note INT NOT NULL, NomClient VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, Email VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, Des VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, IDavis INT AUTO_INCREMENT NOT NULL, INDEX Titre (Titre), INDEX DateEvent (DateEvent), PRIMARY KEY(IDavis)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE client (ID INT AUTO_INCREMENT NOT NULL, Nom VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Prenom VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Sexe VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, DateNaissance VARCHAR(25) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Email VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Adresse VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Handicap TINYINT(1) NOT NULL, DesHandicap VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Tel INT NOT NULL, PRIMARY KEY(ID)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE evenement (Titre VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, DesEvent VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, DateEvent VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, Prix INT NOT NULL, Adresse VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, NbrTotal INT NOT NULL, NbrRestant INT NOT NULL, Idcoach INT NOT NULL, IDevent INT AUTO_INCREMENT NOT NULL, INDEX Idcoach (Idcoach), PRIMARY KEY(IDevent)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE gym (IDgym INT AUTO_INCREMENT NOT NULL, Localisation VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Nomgym VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Capacite INT NOT NULL, NBmembre INT NOT NULL, NBcoach INT NOT NULL, Nomadmin VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, rong INT NOT NULL, PRIMARY KEY(IDgym)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE login (username VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, password VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE profile (IDprofile INT AUTO_INCREMENT NOT NULL, Nomprofile VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Prenomprofile VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, genre VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, DateBirth DATE NOT NULL, Email VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Adresse VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, handicap TINYINT(1) NOT NULL, DesHandicap VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Tel INT NOT NULL, Type VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, PRIMARY KEY(IDprofile)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (IDreservation INT AUTO_INCREMENT NOT NULL, DateReservation DATE NOT NULL, HeureReservation TIME NOT NULL, Localisation INT NOT NULL, Idcoach INT NOT NULL, NomTypeSport VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, dureSeance INT NOT NULL, IDprofile INT NOT NULL, INDEX Idcoach (Idcoach), PRIMARY KEY(IDreservation)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE typesport (IDsport INT AUTO_INCREMENT NOT NULL, NomSport VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Historique VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, TypeEtat VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Description VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, Avis INT NOT NULL, PrixHeure INT NOT NULL, PRIMARY KEY(IDsport)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT evenement_ibfk_1 FOREIGN KEY (Idcoach) REFERENCES coach (Idcoach) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT reservation_ibfk_1 FOREIGN KEY (Idcoach) REFERENCES coach (Idcoach) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY FK_3F596DCCD800119A');
        $this->addSql('DROP INDEX IDX_3F596DCCD800119A ON coach');
        $this->addSql('ALTER TABLE coach CHANGE typesesport_id typesesport INT DEFAULT NULL');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT FK_3F596DCCD800119A FOREIGN KEY (typesesport) REFERENCES diplome (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('CREATE INDEX IDX_3F596DCCD800119A ON coach (typesesport)');
    }
}
