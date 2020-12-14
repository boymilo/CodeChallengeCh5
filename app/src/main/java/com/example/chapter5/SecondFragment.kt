package com.example.chapter5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listener: (CharSequence) -> Unit

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
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title by lazy{view.findViewById<TextView>(R.id.title)}
        val etName by lazy{view.findViewById<EditText>(R.id.etName)}
        val image1 by lazy{view.findViewById<ImageView>(R.id.image1)}
        val image2 by lazy{view.findViewById<ImageView>(R.id.image2)}
        val image3 by lazy{view.findViewById<ImageView>(R.id.image3)}
        title.text = param1
        when(param2){
            "1" ->{
                image1.visibility = View.VISIBLE
            }
            "2" ->{
                image2.visibility = View.VISIBLE
            }
            "3" ->{
                image3.visibility = View.VISIBLE
                etName.visibility = View.VISIBLE
                etName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                        listener(p0)
                    }

                    override fun afterTextChanged(p0: Editable?) {}

                })
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, listener: (CharSequence) -> Unit) =
            SecondFragment().apply {
                this.listener = listener
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}