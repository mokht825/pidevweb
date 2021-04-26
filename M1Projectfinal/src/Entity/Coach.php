<?php

namespace App\Entity;

use App\Repository\CoachRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\Diplome;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=CoachRepository::class)
 */
class Coach
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $Idcoach;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="merci de donner un nom  pour le coach")
     */
    private $Nomcoach;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="merci de donner un prenom pour le coach")
     */
    private $Prenomcoach;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="merci de donner un genre pour le coach")
     */
    private $Genre;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="merci de donner la disponibilité pour le coach")
     */
    private $Disponibilite;


    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="merci de donner une localisation pour le coach")
     */
    private $Localisation;

    /**
     * @ORM\ManyToOne(targetEntity=Diplome::class, inversedBy="coaches")
     *
     */
    private $typesesport;


    public function __construct()
    {

    }
    public function getIdcoach(): ?int
    {
        return $this->Idcoach;
    }

    public function getNomcoach(): ?string
    {
        return $this->Nomcoach;
    }

    public function setNomcoach(string $Nomcoach): self
    {
        $this->Nomcoach = $Nomcoach;

        return $this;
    }

    public function getPrenomcoach(): ?string
    {
        return $this->Prenomcoach;
    }

    public function setPrenomcoach(string $Prenomcoach): self
    {
        $this->Prenomcoach = $Prenomcoach;

        return $this;
    }

    public function getGenre(): ?string
    {
        return $this->Genre;
    }

    public function setGenre(string $Genre): self
    {
        $this->Genre = $Genre;

        return $this;
    }

    public function getDisponibilite(): ?string
    {
        return $this->Disponibilite;
    }

    public function setDisponibilite(string $Disponibilite): self
    {
        $this->Disponibilite = $Disponibilite;

        return $this;
    }



    public function getLocalisation(): ?string
    {
        return $this->Localisation;
    }

    public function setLocalisation(string $Localisation): self
    {
        $this->Localisation = $Localisation;

        return $this;
    }

    public function getTypesesport(): ?Diplome
    {
        return $this->typesesport;
    }

    public function setTypesesport(?Diplome $typesesport): self
    {
        $this->typesesport = $typesesport;

        return $this;
    }




}
