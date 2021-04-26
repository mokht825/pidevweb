<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210423082454 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE coach ADD typesesport_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT FK_3F596DCCD800119A FOREIGN KEY (typesesport_id) REFERENCES diplome (id)');
        $this->addSql('CREATE INDEX IDX_3F596DCCD800119A ON coach (typesesport_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY FK_3F596DCCD800119A');
        $this->addSql('DROP INDEX IDX_3F596DCCD800119A ON coach');
        $this->addSql('ALTER TABLE coach DROP typesesport_id');
    }
}
