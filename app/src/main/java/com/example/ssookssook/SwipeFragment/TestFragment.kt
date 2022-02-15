package com.example.ssookssook.SwipeFragment

import android.net.Uri
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.util.Log.i
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.ssookssook.ImageAdapter
import com.example.ssookssook.R
import com.example.ssookssook.ViewPagerAdapter

import com.example.ssookssook.databinding.FragmentTestBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import kotlinx.coroutines.debug.internal.DebugProbesImplSequenceNumberRefVolatile
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "dayStr"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    private var binding: FragmentTestBinding? = null

    private var imageUrlList = ArrayList<String>();
    private var imagelist:MutableLiveData<ArrayList<String>> = MutableLiveData();

    lateinit var  myadapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        var day = arguments?.getInt("day")
        Log.i("day:",day.toString())

        val storage = Firebase.storage.getReferenceFromUrl("gs://ssookssook-bd202.appspot.com/");
        val listRef = storage.child("images/2022/1/" + day.toString()); // 1월 데이터만 표시해주면됨

        listRef.listAll().addOnSuccessListener { (items, prefixes) ->
            if (items.size== 0)
            {

                storage.child("images/datas/nono.jpg").downloadUrl.addOnSuccessListener {
                    imageUrlList.add(Uri.parse(it.toString()).toString())
                    //return@addOnSuccessListener
                }

                //imagelist.postValue(imageUrlList)
            }
            items.forEach{ //여기서 items 란 실제 jpg 파일들을 얘기함

                    it.downloadUrl.addOnSuccessListener {
                        imageUrlList.add(Uri.parse(it.toString()).toString());

                    }.addOnFailureListener{
                        Log.i("failed","failed")
                    }
                }
                imagelist.postValue(imageUrlList)
            }
            .addOnFailureListener {
                Log.i("fail",it.toString())
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test,container,false)
        return binding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //data가 새롭게 들어올때마다
        imagelist.observe(viewLifecycleOwner, Observer {

            val recyclerView = binding!!.recyclerviewImage

            val layoutManager = StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL)
            recyclerView.layoutManager = layoutManager

            myadapter = ImageAdapter(requireContext(), it)
            recyclerView.adapter = myadapter
        })

    }
}