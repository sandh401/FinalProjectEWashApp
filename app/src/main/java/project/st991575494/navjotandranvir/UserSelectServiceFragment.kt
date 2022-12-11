package project.st991575494.navjotandranvir

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.st991575494.navjotandranvir.Data.Service
import project.st991575494.navjotandranvir.ViewModels.UserViewModel
import project.st991575494.navjotandranvir.databinding.FragmentUserSelectBinding

// This class is reponsible to show the fragment for Selecting the service page
class UserSelectServiceFragment : Fragment() {

    companion object {
        fun newInstance() = UserSelectServiceFragment()
    }

    private lateinit var viewModel: UserViewModel
    private var _binding: FragmentUserSelectBinding? = null
    private val binding get() = _binding!!
    private var isFirst = true;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserSelectBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel

        binding.spinnerVehicle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                if (!isFirst) {
                    val vehcileArray = getResources().getStringArray(R.array.vehicle_type);
                    val vehicle = vehcileArray[position]
                        if (vehicle.equals("SEDAN")) {
                            binding.imageViewUserSelect.setBackgroundResource(R.drawable.sedan)
                            binding.imageViewUserSelect.background =
                                resources.getDrawable(R.drawable.button_theme)
                        }
                        if (vehicle.equals("Hatch Back")) {
                            binding.imageViewUserSelect.setBackgroundResource(R.drawable.hatchback)
                            binding.imageViewUserSelect.background =
                                resources.getDrawable(R.drawable.button_theme)
                        }

                        if (vehicle.equals("SUV")) {
                            binding.imageViewUserSelect.setBackgroundResource(R.drawable.suv)
                            binding.imageViewUserSelect.background =
                                resources.getDrawable(R.drawable.button_theme)
                        }


                }
                isFirst = false
            }
        }



        binding.btnSelectService.setOnClickListener {

            val confirmBookingFragment = UserConfirmBookingFragment()
            val bundle = Bundle()
            val obj: Service = Service(binding.spinnerVehicle.selectedItem.toString(), binding.spinnerService.selectedItem.toString(), binding.editTextDate.calendarView.date.toString())
            bundle.putSerializable("service", obj)
            confirmBookingFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, confirmBookingFragment)?.commit()
        }




        return  binding.root

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Pick option")
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
    }



}