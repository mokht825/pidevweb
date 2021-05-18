<?php

namespace App\Form;

use App\Entity\Coach;
use App\Entity\Diplome;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\ColorType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CoachType extends AbstractType
{


    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Nomcoach')
            ->add('Prenomcoach')
            ->add('Genre')
            ->add('Disponibilite')
            ->add('typesesport', EntityType::class, [
                        'class' => Diplome::class,
                        'choice_label' => 'nom',
                        'label'=>'Diplome'])
            ->add('Localisation')
            ->add('Save',SubmitType::class);

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Coach::class,
        ]);
    }
}
