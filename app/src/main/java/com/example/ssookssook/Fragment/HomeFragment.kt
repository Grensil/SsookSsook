package com.example.ssookssook.Fragment

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ssookssook.R
import com.example.ssookssook.SwipeFragment.TestFragment
import com.example.ssookssook.SwipeFragment.TestFragment2
import com.example.ssookssook.ViewPagerAdapter
import com.example.ssookssook.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentHomeBinding? = null

    //var viewList = ArrayList<View>() //view를 3개 저장하기 위한 공간
    private var fragmentList = ArrayList<Fragment>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰페이저
        val viewPager2= binding!!.imgListViewPager;
        //day는 처음 존재하는 날짜 20일임
        val adapter = ViewPagerAdapter(requireActivity(),20);
        viewPager2.adapter = adapter


//        val bundle= Bundle();
//        bundle.putString("day", "21/")
//        val testFra = TestFragment();
//        testFra.arguments = bundle;
//
//        val testFra2 = TestFragment2();
//
//        testFra.arguments = bundle;
//        var exist_days = 3
//        for (days in 1..exist_days)
//        {
//            adapter.addFragment(TestFragment());
//        }
//
//        viewPager2.adapter = adapter;





    }




}


