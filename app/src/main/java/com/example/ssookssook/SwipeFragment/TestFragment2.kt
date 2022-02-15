package com.example.ssookssook.SwipeFragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ssookssook.ImageAdapter
import com.example.ssookssook.R
import com.example.ssookssook.databinding.FragmentTest2Binding
import com.example.ssookssook.databinding.FragmentTestBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import com.google.firebase.storage.ktx.storage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "dayStr"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestFragment2() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentTest2Binding? = null

    private var imageUrlList = ArrayList<String>();
    private var imagelist: MutableLiveData<ArrayList<String>> = MutableLiveData();

    lateinit var  myadapter : ImageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val storage = Firebase.storage.getReferenceFromUrl("gs://ssookssook-bd202.appspot.com/");
        val listRef = storage.child("images/2022/1/" + param1); // 1월 데이터만 표시해주면됨

        listRef.listAll().addOnSuccessListener { (items, prefixes) ->
            items.forEach{
                it.downloadUrl.addOnSuccessListener {
                    imageUrlList.add(Uri.parse(it.toString()).toString());

                }.addOnFailureListener{
                    Log.i("failed","failed")
                }
                imagelist.postValue(imageUrlList)
            }
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test2,container,false)
        return binding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagelist.observe(viewLifecycleOwner, Observer {
            val recyclerView = binding!!.recyclerviewImage
            val layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.layoutManager = layoutManager

            myadapter = ImageAdapter(requireContext(), it)
            recyclerView.adapter = myadapter
        })

    }
}