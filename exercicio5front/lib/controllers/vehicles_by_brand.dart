import 'package:exercicio5front/model/brand.dart';
import 'package:exercicio5front/model/vehicles.dart';
import 'package:exercicio5front/provider/vehicle_provider.dart';
import 'package:get/get.dart';

class VehicleByBrandController extends GetxController {
  VehicleByBrandController();
  VehicleProvider vehicleProvider = VehicleProvider();

  final RxList<Vehicle> _vehiclesByBrand = <Vehicle>[].obs;
  final Rx<Brand> _brand = Brand.audi.obs;

  List<Vehicle> get vehiclesByBrand => _vehiclesByBrand.toList();

  Brand get brand => _brand.value;

  @override
  void onReady() {
    super.onReady();
    getVehiclesByBrand();
  }

  void changeBrand(Brand newBrand) {
    _brand.value = newBrand;
    getVehiclesByBrand();
  }

  Future<void> getVehiclesByBrand() async {
    final vehicles =
        await vehicleProvider.getVehiclesByBrand(brand.name.toUpperCase());

    _vehiclesByBrand.assignAll(vehicles);
  }
}
