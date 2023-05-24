package com.borja.android.horoscopapp.ui.lucky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.borja.android.horoscopapp.R
import com.borja.android.horoscopapp.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private val viewModel by viewModels<LuckyViewModel>()
    private var _binding: FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var randomCardsProvider:RandomCardsProvider


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.tvLuckyInfo.animate().alpha(1f).duration = 1500
        //binding.tvLuckyInfo.animate().rotation(360f).duration = 1500
        //val rotationCremita = AnimationUtils.loadAnimation(requireContext(), R.anim.rotatito)
        //binding.tvLuckyInfo.startAnimation(rotationCremita)

        binding.viewFrontContainer.ivLuckyCard
        binding.viewBackContainer.viewBack.setOnClickListener {
            prepareCard()
            flipCard()
        }
    }

    private fun flipCard() {
        try {
            //Visibilidad
            binding.viewFrontContainer.viewFront.isVisible = true

            //Efecto 3D
            val scale = requireContext().resources.displayMetrics.density
            val cameraDist = 8000 * scale
            binding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            binding.viewBackContainer.viewBack.cameraDistance = cameraDist

            //Recuperamos la animacion Flip Out
            val flipOutAnimatorSet = AnimatorInflater.loadAnimator(
                requireContext(), R.animator.flip_out
            ) as AnimatorSet

            flipOutAnimatorSet.setTarget(binding.viewBackContainer.viewBack)

            //Recuperamos y seteamos la animación Flip In

            val flipInAnimatorSet = AnimatorInflater.loadAnimator(
                requireContext(), R.animator.flip_in
            ) as AnimatorSet

            flipInAnimatorSet.setTarget(binding.viewFrontContainer.viewFront)

            //Iniciamos las animaciones

            flipInAnimatorSet.doOnStart {
                binding.tvLuckyInfo.animate().alpha(1f).duration = 1500
            }

            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()


            flipInAnimatorSet.doOnEnd {
                binding.viewBackContainer.viewBack.isVisible = false

            }


        } catch (e: java.lang.Exception) {
        Log.e("Borchx", e.toString())
        }
    }

    private fun prepareCard() {
    val luck = randomCardsProvider.getLucky()
        binding.viewFrontContainer.ivLuckyCard.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                luck.image
            )
        )
        binding.tvLuckyInfo.text = getString(luck.text)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }

}