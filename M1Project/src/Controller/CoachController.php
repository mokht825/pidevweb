<?php

namespace App\Controller;

use App\Entity\Coach;
use App\Form\CoachType;
use App\Repository\CoachRepository;

use App\Repository\DiplomeRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/coach")
 */
class CoachController extends AbstractController
{

    /**
     * @param CoachRepository $repository
     * @param Request $request
     * @return Response
     * @Route("/search",name="search")
     *
     *
     */
    public function searchCoach(CoachRepository $repository, Request $request)
    {

        $ccoach='';
        $requestString=$request->get('searchValue');
        if(strlen($requestString)>0) {
            $coach = $repository->findCoachByNom($requestString);

        }
        else
        { $coach = $repository->findAll();}

        $encoders = [new XmlEncoder(), new JsonEncoder()];
        $normalizers = [new ObjectNormalizer()];

        $serializer = new Serializer($normalizers, $encoders);
        $jsonContent = $serializer->serialize($coach, 'json', ['ignored_attributes' => ['typesesport']]);


        $response = new Response(json_encode($jsonContent));
        $response->headers->set('Content-Type', 'application/json; charset=utf-8');

        return $response;
    }



    /**
     * @Route("/", name="coach_index", methods={"GET"})
     * @param CoachRepository $coachRepository
     * @param DiplomeRepository $DiplomeRepository
     *
     * @return Response
     */
    public function index(CoachRepository $coachRepository, DiplomeRepository $DiplomeRepository): Response
    {

        $coaches = $coachRepository->findAll();
        $typeSports = $DiplomeRepository->findAll();
        $typeSportNom = [];
        $typeSportColor = [];
        $typeSportCount = [];
        foreach($typeSports as $typeSport){
            $typeSportNom[] = $typeSport->getNom();
            $typeSportColor[] = $typeSport->getColor();
            $typeSportCount[] = count($typeSport->getCoaches());
        }


        return $this->render('admin/coach/index.html.twig', [
            'coaches' => $coaches,
            'typeSportNom' => json_encode($typeSportNom),
            'typeSportColor' => json_encode($typeSportColor),
            'typeSportCount' => json_encode($typeSportCount),
        ]);
    }
    /**
     * @Route("/C", name="coach_indexC", methods={"GET"})
     */
    public function indexC(CoachRepository $coachRepository): Response
    {
        return $this->render('coach/index.html.twig', [
            'coaches' => $coachRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="coach_new", methods={"GET","POST"})
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $coach = new Coach();
        $form = $this->createForm(CoachType::class, $coach);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($coach);
            $entityManager->flush();
            $flashy->success('Coach Added!', 'http://your-awesome-link.com');
            return $this->redirectToRoute('coach_index');

        }

        return $this->render('admin/coach/new.html.twig', [
            'coach' => $coach,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/{Idcoach}", name="coach_show", methods={"GET"})
     */
    public function show(Coach $coach): Response
    {
        return $this->render('admin/coach/show.html.twig', [
            'coach' => $coach,
        ]);
    }

    /**
     * @Route("/pdf/{Idcoach}", name="coach_pdf", methods={"GET"})
     */
    public function Coachpdf(Coach $coach): Response
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('admin/coach/coachPdf.html.twig', [
            'coach' => $coach,
        ]);
        $html .= '<link type="text/css" href="/front/css/pdf.css" rel="stylesheet" />';
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mmypdf.pdf", [
            "Attachment" => true
        ]);

    }


    /**
     * @Route("/C/{Idcoach}", name="coach_showC", methods={"GET"})
     */
    public function showC(Coach $coach): Response
    {
        return $this->render('coach/show.html.twig', [
            'coach' => $coach,
        ]);
    }


    /**
     * @Route("/{Idcoach}/edit", name="coach_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Coach $coach): Response
    {
        $form = $this->createForm(CoachType::class, $coach);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('coach_index');
        }

        return $this->render('admin/coach/edit.html.twig', [
            'coach' => $coach,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{Idcoach}", name="coach_delete", methods={"POST"})
     */
    public function delete(Request $request, Coach $coach): Response
    {
        if ($this->isCsrfTokenValid('delete'.$coach->getIdcoach(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($coach);
            $entityManager->flush();
        }

        return $this->redirectToRoute('coach_index');
    }


}
