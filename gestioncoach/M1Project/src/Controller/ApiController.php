<?php


namespace App\Controller;

use App\Entity\Coach;
use App\Entity\Diplome;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Json;

class ApiController extends  AbstractController
{

    //Diplome


    /******************Ajouter Diplome*****************************************/
    /**
     * @Route("addDiplome", name="add_diplome")
     */
    public function ajouterDiplome(Request $request)
    {
        $diplome = new Diplome();

        $em = $this->getDoctrine()->getManager();
        $diplome->setNom($request->get("nom"));
        $diplome->setColor($request->get("color"));
        $em->persist($diplome);

        $em->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($diplome);
        return new JsonResponse($formatted);
    }



    /******************Supprimer Coach*****************************************/

    /**
     * @Route("/deleteDiplome", name="delete_diplome")
     */

    public function deleteDiplomeAction(Request $request) {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $coach = $em->getRepository(Diplome::class)->find($id);



        if($coach!=null ) {
            $em->remove($coach);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Diplome a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id Diplome invalide.");


    }


    /******************updateDiplome*****************************************/
    /**
     * @Route("/updateDiplome", name="update_Diplome")
     */
    public function updateDiplome(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $coach = $this->getDoctrine()->getManager()
            ->getRepository(Diplome::class)
            ->find($request->get("id"));


        $coach->setNom($request->get("nom"));
        $coach->setColor($request->get("color"));


        $em->persist($coach);
        $em->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($coach);
        return new JsonResponse($formatted);

    }




    /******************Ajouter Coach*****************************************/
    /**
     * @Route("addCoach", name="add_coach")
     */
    public function ajouterCoach(Request $request)
    {
        $coach = new Coach();
        $Nomcoach = $request->query->get("Nomcoach");
        $Prenomcoach = $request->query->get("Prenomcoach");
        $Genre = $request->query->get("Genre");
        $Localisation = $request->query->get("Localisation");
        $Disponibilite = $request->query->get("Disponibilite");
        $typesesport = $request->query->get("typesesport");
        $type = $this->getDoctrine()->getManager()->getRepository(Diplome::class)->find($typesesport);

        $em = $this->getDoctrine()->getManager();

        $coach->setNomcoach($Nomcoach);
        $coach->setPrenomcoach($Prenomcoach);
        $coach->setGenre($Genre);
        $coach->setDisponibilite($Disponibilite);
        $coach->setLocalisation($Localisation);
        $coach->setTypesesport($type);


        $em->persist($coach);
        $em->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($coach);
        return new JsonResponse($formatted);
    }

    /******************Supprimer Coach*****************************************/

    /**
     * @Route("/deleteCoach", name="delete_coach")
     */

    public function deleteCoachAction(Request $request) {
        $id = $request->get("Idcoach");

        $em = $this->getDoctrine()->getManager();
        $coach = $em->getRepository(Coach::class)->find($id);
        if($coach!=null ) {
            $em->remove($coach);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Coach a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id Coach invalide.");


    }

    /******************Modifier Coach*****************************************/
    /**
     * @Route("/updateCoach", name="update_Coach")
     */
    public function modifierCoachAction(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $coach = $this->getDoctrine()->getManager()
            ->getRepository(Coach::class)
            ->find($request->get("Idcoach"));


        $Nomcoach = $request->query->get("Nomcoach");
        $Prenomcoach = $request->query->get("Prenomcoach");
        $Genre = $request->query->get("Genre");
        $Localisation = $request->query->get("Localisation");
        $Disponibilite = $request->query->get("Disponibilite");
        $typesesport = $request->query->get("typesesport");
        $coach->setNomcoach($Nomcoach);
        $coach->setPrenomcoach($Prenomcoach);
        $coach->setGenre($Genre);
        $coach->setDisponibilite($Disponibilite);
        $coach->setLocalisation($Localisation);
        $coach->setTypesesport($em->getRepository(Diplome::class)->find($typesesport));

        $em->persist($coach);
        $em->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($coach);
        return new JsonResponse($formatted);

    }



    /******************affichage Coach*****************************************/

    /**
     * @Route("/displayCoach", name="display_coach")
     */
    public function allRecAction()
    {

        $coach= $this->getDoctrine()->getManager()->getRepository(Coach::class)->findAll();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($coach);
        return new JsonResponse($formatted);

    }


    /******************Detail Coach*****************************************/

    /**
     * @Route("/detailCoach", name="detail_coach")
     */

    //Detail Reclamation
    public function detailCoachAction(Request $request)
    {
        $id = $request->get("Idcoach");

        $em = $this->getDoctrine()->getManager();
        $coach = $this->getDoctrine()->getManager()->getRepository(Coach::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($coach);
        return new JsonResponse($formatted);
    }

    //recherche coach

    /**
     * @Route("/chercherCoach", name="search_coach")
     */
    public function chercherCoachAction(Request $request)
    {
        $Nomcoach = $request->query->get("Nomcoach");

        $id = $this->getDoctrine()->getManager()->getRepository(Coach::class)->findBy(["Nomcoach"=>$Nomcoach]);

        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($id);
        return new JsonResponse($formatted);
    }






    //get typeSport Diplome

    /**
     * @Route("/getIdDiplomeByNom", name="type_sport")
     */
    public function getIdDiplomeByNom(Request $request)
    {
        $typesesport = $request->query->get("nom");

        $em = $this->getDoctrine()->getManager();
        $id = $this->getDoctrine()->getManager()->getRepository(Diplome::class)->findOneBy(["nom"=>$typesesport]);

        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($id);
        return new JsonResponse($formatted);
    }






    /******************************displayDiplome*****************************/

    /**
     * @Route("/displayDiplome", name="display_diplome")
     */
    public function displayDiplomeAction()
    {

        $coach= $this->getDoctrine()->getManager()->getRepository(Diplome::class)->findAll();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($coach);
        return new JsonResponse($formatted);

    }







    //statsitques

    /**
     * @Route("/countCoach", name="count_coach")
     */
    public function countCoachAction()
    {

        $coach= $this->getDoctrine()->getManager()->getRepository(Coach::class)->findAll();
        $count = 0;
        while($count < sizeof($coach)) {
            $count++;
        }
        $encoder = new JsonEncoder();

        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($count);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/countDiplome", name="countDiplome")
     */
    public function countDiplomeAction()
    {

        $d= $this->getDoctrine()->getManager()->getRepository(Diplome::class)->findAll();
        $count = 0;
        while($count < sizeof($d)) {
            $count++;
        }
        $encoder = new JsonEncoder();

        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object;
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($count);
        return new JsonResponse($formatted);

    }







}