import 'package:exercicio5front/controllers/create_vehicle.dart';
import 'package:exercicio5front/controllers/edit_vehicle.dart';
import 'package:exercicio5front/controllers/vehicles_by_brand.dart';
import 'package:exercicio5front/controllers/vehicles_by_decade.dart';
import 'package:get/get.dart';

class VehiclesBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut<VehicleByBrandController>(() => VehicleByBrandController());
    Get.lazyPut<VehiclesByDecadeController>(() => VehiclesByDecadeController());
    Get.lazyPut<EditVehicleController>(() => EditVehicleController(),
        fenix: true);
    Get.lazyPut<CreateVehicleController>(() => CreateVehicleController(),
        fenix: true);
  }
}
