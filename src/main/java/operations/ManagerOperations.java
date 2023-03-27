package operations;

import data.Data;
import exceptions.*;
import input.TakeInput;
import product.Assembly;
import product.Material;
import product.Part;
import product.Product;
import users.Employee;
import users.Manager;
import users.User;


public class ManagerOperations {

    public Assembly createAssembly(User user, Material parent) {
        if (user instanceof Manager) {
            Manager manager = (Manager) user;
            if (parent instanceof Assembly) {
                Assembly assemblyCast = (Assembly) parent;
                Assembly assembly = createAssemblyForAssembly(manager, assemblyCast);
                return assembly;
            } else if (parent instanceof Product) {
                Product product = (Product) parent;
                Assembly assembly = createAssemblyForProduct(manager, product);
                return assembly;
            } else {
                System.out.println("Part can not be a parent");
                return null;

            }

        } else {
            System.out.println("You should be a manager to create an Assembly");
            return null;
        }
    }

    private Assembly createAssemblyForAssembly(Manager manager, Assembly assemblyParent) {
        System.out.println("Please enter assembly title you want to add:");
        String title = takeInfoMaterial();
        Assembly assembly = new Assembly(title, manager, assemblyParent);
        Data.assemblies.add(assembly);
        assembly.getChildren().add(assembly);
        return assembly;

    }

    private Assembly createAssemblyForProduct(Manager manager, Product product) {
        System.out.println("Please enter assembly title you want to add:");
        String title = takeInfoMaterial();
        Assembly assembly = new Assembly(title, manager, product);
        Data.assemblies.add(assembly);
        product.getChildren().add(assembly);
        return assembly;

    }

    private String takeInfoMaterial() {
        TakeInput input = new TakeInput();
        String title = input.strInput();
        return title;
    }

    public Part createPart(User user, Material parent) {
        if (user instanceof Manager) {
            Manager manager = (Manager) user;
            if (parent instanceof Assembly) {
                Assembly assembly = (Assembly) parent;
                Part part = createPartForAssembly(manager, assembly);
                return part;
            } else if (parent instanceof Product) {
                Product product = (Product) parent;
                Part part = createPartForProduct(manager, product);
                return part;
            } else {
                System.out.println("You can not add a part to a part");
                return null;

            }
        } else {
            System.out.println("You should be a manager to create an Assembly");
            return null;
        }

    }

    private Part createPartForAssembly(Manager manager, Assembly assembly) {
        System.out.println("Please enter part title you want to add:");
        String title = takeInfoMaterial();
        System.out.println("Please enter employee name you want to assign to " + title + ":");
        String empName = takeInfoMaterial();
        Part part = new Part(title, manager, assembly, empName);
        assembly.getChildren().add(part);
        Data.parts.add(part);
        return part;
    }

    private Part createPartForProduct(Manager manager, Product product) {
        System.out.println("Please enter part title you want to add:");
        String title = takeInfoMaterial();
        System.out.println("Please enter employee name you want to assign to " + title + ":");
        String empName = takeInfoMaterial();
        Part part = new Part(title, manager, product, empName);
        product.getChildren().add(part);
        Data.parts.add(part);
        return part;
    }

    public void ManOp(User user, Material product) {
        TakeInput takeInput = new TakeInput();
        if (user instanceof Manager) {
            Manager manager = (Manager) user;
            System.out.println("You are processing on this " + product.getClass().getSimpleName() + ": " + product.getTitle() + " with " + product.getId() + " id");
            System.out.println("What do you want to do? (0-EXIT PARENT 1-ADD ASSEMBLY 2-ADD PART)");
            String forAssembly = takeInput.strInput();
            if (forAssembly.equals("0")) {// press 0 to EXIT from PARENT!
                if (product instanceof Assembly) {
                    Assembly assembly = (Assembly) product;
                    if (assembly.checkNumberOfChildren()) {
                        ManOp(manager, assembly.getParent());
                    } else {
                        try {
                            throw new NotEnoughChildrenException("You dont have enough material to EXIT");
                        } catch (NotEnoughChildrenException e) {
                            e.getMessage();
                        }
                        System.out.println("You have to add at least " + (2 - assembly.getChildren().size()) + " assembly or part");
                        ManOp(manager, assembly);
                    }

                } else if (product instanceof Product) {
                    Product product1 = (Product) product;
                    if ((product1.checkNumberOfChildren())) {
                        System.out.println(product1.getTitle() + " is created");

                    } else {
                        try {
                            throw new NotEnoughChildrenException("You dont have enough children for EXIT PARENT");
                        } catch (NotEnoughChildrenException e) {
                            e.getMessage();
                        }
                        System.out.println("You have to add at least " + (2 - product1.getChildren().size()) + " assembly or part");
                        ManOp(manager, product1);
                    }

                } else {
                    try {
                        throw new CanNotAttachMaterialToPartException("You can not attach a material to a part");
                    } catch (CanNotAttachMaterialToPartException e) {
                        e.getMessage();
                    }
                }

            } else if (forAssembly.equals("1")) {// press 1 to add assembly
                Assembly assembly = createAssembly(manager, product);
                if (assembly != null) {
                    ManOp(manager, assembly);
                } else {
                    try {
                        throw new MaterialCouldNotBeCreatedException("Part could not be created");
                    } catch (MaterialCouldNotBeCreatedException e) {
                        e.getMessage();
                    }
                }


            } else if (forAssembly.equals("2")) {// press 2 to add part
                Part part = createPart(manager, product);
                if (part != null) {
                    ManOp(manager, part.getParent());
                } else {
                    try {
                        throw new MaterialCouldNotBeCreatedException("Part could not be created");
                    } catch (MaterialCouldNotBeCreatedException e) {
                        e.getMessage();
                    }
                }


            }
        } else {
            try {
                throw new UnauthorizedUserOperationException("You don't have authority to add assembly");
            } catch (UnauthorizedUserOperationException e) {
                e.getMessage();
            }
        }

    }

    public void seeRelatedEmployees(Manager manager) {
        System.out.println("Related Employees List:");
        helperSeeEmployess(manager);
    }

    private void helperSeeEmployess(Manager manager) {
        if (!Data.parts.isEmpty()) {
            for (int i = 0; i < Data.parts.size(); i++) {
                if (Data.parts.get(i).getRelatedManager().getId().equals(manager.getId())) {
                    System.out.println("Employee name: " + Data.parts.get(i).getRelatedEmployee().getName() + " Employee id: " + Data.parts.get(i).getRelatedEmployee().getId() + " Related part: " + Data.parts.get(i).getTitle());
                }
            }

        } else {
            try {
                throw new PartNotCreatedException("There is no employee because no part is created");
            } catch (PartNotCreatedException e) {
                e.getMessage();
            }
        }
    }

    public void seeProductTree(Product product) {
        System.out.println("Product Tree:");
        product.toStringProduct();
    }


}


