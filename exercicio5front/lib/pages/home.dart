import 'package:exercicio5front/controllers/home.dart';
import 'package:exercicio5front/pages/base_page.dart';
import 'package:exercicio5front/pages/widgets/vehicles_list.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class HomePage extends GetView<HomeController> {
  @override
  Widget build(BuildContext context) {
    return PopScope(
      onPopInvoked: (didPop) {
        controller.poped.value = true;
      },
      canPop: true,
      child: BasePage(
        content: Column(
          children: [
            Obx(
              () => Text("Veículos não vendidos ${controller.notSoldVehicles}"),
            ),
            Obx(
              () {
                return VehicleList(
                  vehicles: controller.vehiclesLastWeek,
                );
              },
            ),
            IconButton(
              onPressed: () => {Get.offNamed('/create')},
              icon: const Icon(
                Icons.add,
              ),
            )
          ],
        ),
      ),
    );
  }
}
