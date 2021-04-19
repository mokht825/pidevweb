<?php

namespace App\Controller;

use App\Entity\Diplome;
use App\Form\DiplomeType;
use App\Repository\DiplomeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/type/sport")
 */
class DiplomeController extends AbstractController
{
    /**
     * @Route("/", name="type_sport_index", methods={"GET"})
     */
    public function index(DiplomeRepository $DiplomeRepository): Response
    {
        return $this->render('admin/type_sport/index.html.twig', [
            'type_sports' => $DiplomeRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="type_sport_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $typeSport = new Diplome();
        $form = $this->createForm(DiplomeType::class, $typeSport);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($typeSport);
            $entityManager->flush();

            return $this->redirectToRoute('type_sport_index');
        }

        return $this->render('admin/type_sport/new.html.twig', [
            'type_sport' => $typeSport,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="type_sport_show", methods={"GET"})
     */
    public function show(Diplome $typeSport): Response
    {
        return $this->render('admin/type_sport/show.html.twig', [
            'type_sport' => $typeSport,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="type_sport_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Diplome $typeSport): Response
    {
        $form = $this->createForm(DiplomeType::class, $typeSport);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('type_sport_index');
        }

        return $this->render('admin/type_sport/edit.html.twig', [
            'type_sport' => $typeSport,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="type_sport_delete", methods={"POST"})
     */
    public function delete(Request $request, Diplome $typeSport): Response
    {
        if ($this->isCsrfTokenValid('delete'.$typeSport->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($typeSport);
            $entityManager->flush();
        }

        return $this->redirectToRoute('type_sport_index');
    }
}
