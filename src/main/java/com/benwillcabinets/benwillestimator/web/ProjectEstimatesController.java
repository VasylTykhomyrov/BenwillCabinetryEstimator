package com.benwillcabinets.benwillestimator.web;

import com.benwillcabinets.benwillestimator.domain.Category;
import com.benwillcabinets.benwillestimator.domain.Product;
import com.benwillcabinets.benwillestimator.domain.ProjectEstimate;
import com.benwillcabinets.benwillestimator.domain.ProjectItem;
import com.benwillcabinets.benwillestimator.domain.Transaction;
import com.benwillcabinets.benwillestimator.refacing.RefacingInfo;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import com.benwillcabinets.benwillestimator.service.PdfGenerator;
import com.benwillcabinets.benwillestimator.service.ProductService;
import com.benwillcabinets.benwillestimator.service.ProjectEstimateService;
import com.benwillcabinets.benwillestimator.service.RefacingItemService;
import com.benwillcabinets.benwillestimator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
public class ProjectEstimatesController {
    @Autowired
    private ProjectEstimateService projectEstimateService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RefacingItemService refacingItemService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PdfGenerator pdfGenerator;

    @PostMapping("/projects")
    ProjectEstimate addProject(@RequestBody ProjectEstimate project) {
        project.setDateCreated(new Date());
        return projectEstimateService.save(project);
    }

    @DeleteMapping("/projects/{id}")
    ResponseEntity<Void> deleteProject(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        projectEstimateService.delete(project);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/projects/{id}/items")
    ProjectEstimate addProjectItem(@PathVariable("id") int projectId, @RequestBody Product product) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        Product productFound = productService.findById(product.getId()).get();
        ProjectItem item = new ProjectItem();
        item.setProduct(productFound);
        item.setSellProjectPrice(toBigDecimal(productFound.getSellPrice()));
        item.setCostProjectPrice(toBigDecimal(productFound.getCostPrice()));
        item.setQty(1);
        project.getListOfProducts().add(item);
        projectEstimateService.save(project);
        return project;
    }

    @PostMapping("/projects/{id}/refacing-info")
    ProjectEstimate saveRefacingInfo(@PathVariable("id") int projectId, @RequestBody RefacingInfo info) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        RefacingInfo currentInfo = project.getRefacingInfo();
        if(currentInfo == null) {
            currentInfo = info;
        } else {
            currentInfo.setColour(info.getColour());
            currentInfo.setHandles(info.getHandles());
            currentInfo.setStyle(info.getStyle());
        }
        project.setRefacingInfo(currentInfo);
        project.getListOfRefacingItems().stream()
                .forEach(r -> r.setCostPSF(project.getRefacingInfo().getSFPrice()));
        projectEstimateService.save(project);
        return project;
    }

    @PostMapping("/projects/{id}/transactions")
    ProjectEstimate addTransaction(@PathVariable("id") int projectId, @RequestBody Transaction trans) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        transactionService.save(trans);
        project.getListOfTransactions().add(trans);
        projectEstimateService.save(project);
        return project;
    }

    @DeleteMapping("/projects/{id}/transactions/{transId}")
    ProjectEstimate removeTransaction(@PathVariable("id") int projectId, @PathVariable("transId") int transId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        Transaction transactionToRemove = findTransaction(transId, project);
        project.getListOfTransactions().remove(transactionToRemove);
        projectEstimateService.save(project);
        return project;
    }


    @PutMapping("/projects/{id}/transactions/{transId}")
    ProjectEstimate updateTransaction(@PathVariable("id") int projectId, @PathVariable("transId") int transId,
                                      @RequestBody Transaction transaction) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        Transaction transactionToUpdate = findTransaction(transId, project);
        transactionToUpdate.setNote(transaction.getNote());
        transactionToUpdate.setAllocatedFor(transaction.getAllocatedFor());
        projectEstimateService.save(project);
        return project;
    }

    @GetMapping("/projects/{id}/transactions")
    List<Transaction> getTransactions(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfTransactions();
    }

    @PostMapping("/projects/{id}/refacing-items")
    ProjectEstimate addProjectItem(@PathVariable("id") int projectId, @RequestBody RefacingItem item) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        item.setCostPSF(project.getRefacingInfo().getSFPrice());
        refacingItemService.save(item);
        project.getListOfRefacingItems().add(item);
        projectEstimateService.save(project);
        return project;
    }

    @DeleteMapping("/projects/{id}/refacing-items/{itemId}")
    ProjectEstimate removeRefacingItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        RefacingItem itemToDelete = findRefacingItem(itemId, project);
        project.getListOfRefacingItems().remove(itemToDelete);
        projectEstimateService.save(project);
        return project;
    }

    private void calculateCostPSF(RefacingItem item) {
        // calculate based on colour and style
        item.setCostPSF(23);
    }

    @PutMapping("/projects/{id}/items/{itemId}")
    ProjectEstimate updateProductItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId, @RequestBody ProjectItem projectItem) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        ProjectItem itemToUpdate = findProjectItem(itemId, project);
        itemToUpdate.setCostProjectPrice(projectItem.getCostProjectPrice());
        itemToUpdate.setSellProjectPrice(projectItem.getSellProjectPrice());
        itemToUpdate.setQty(projectItem.getQty());
        itemToUpdate.setPrintable(projectItem.isPrintable());
        itemToUpdate.setDuration(projectItem.getDuration());
        itemToUpdate.setAssignedTo(projectItem.getAssignedTo());
        itemToUpdate.setScheduledFor(projectItem.getScheduledFor());
        itemToUpdate.setPaid(projectItem.isPaid());
        itemToUpdate.setCompleted(projectItem.isCompleted());
        itemToUpdate.setPaidBy(projectItem.getPaidBy());
        projectEstimateService.save(project);
        return project;
    }

    @PutMapping("/projects/{id}")
    ProjectEstimate createRefacingProduct(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();

        String productName = "Refacing project - " + project.getAddress();
        Optional<Product> productFound = productService.findByName(productName);

        RefacingInfo info = project.getRefacingInfo();
        double sfPrice = info.getSFPrice();
        double costPrice = project.getListOfRefacingItems().stream()
                .mapToDouble(item-> item.getWidth() * item.getHeight() / 144 * sfPrice).sum();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Product refacingProduct = new Product();
        refacingProduct.setCategory(Category.CABINETRY);
        refacingProduct.setName(productName);
        refacingProduct.setUOM("1");
        refacingProduct.setDescription("Created on: " + sdf.format(new Date()));
        refacingProduct.setQty(1);
        refacingProduct.setCostPrice((int)costPrice);
        refacingProduct.setSellPrice((int)(costPrice * 2.25));
        productService.save(refacingProduct);

        if(productFound.isPresent()){
            List<ProjectItem> items =  project.getListOfProducts();
            items.forEach(i -> replaceProduct(i, productFound.get(), refacingProduct));
            productService.delete(productFound.get());
        }

        projectEstimateService.save(project);
        return project;
    }

    private void replaceProduct(ProjectItem item, Product productToMatch, Product refacingProduct) {
        if(item.getProduct().getId().equals(productToMatch.getId())){
            item.setProduct(refacingProduct);
            item.setCostProjectPrice(toBigDecimal(refacingProduct.getCostPrice()));
            item.setSellProjectPrice(toBigDecimal(refacingProduct.getSellPrice()));
        }
    }

    private BigDecimal toBigDecimal(double value) {
        return  BigDecimal.valueOf(value).setScale(2, RoundingMode.CEILING);
    }


    @DeleteMapping("/projects/{id}/items/{itemId}")
    ProjectEstimate removeProductItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        ProjectItem itemToDelete = findProjectItem(itemId, project);
        project.getListOfProducts().remove(itemToDelete);
        projectEstimateService.save(project);
        return project;
    }

    private RefacingItem findRefacingItem(int itemId, ProjectEstimate project) {
        RefacingItem itemToDelete = null;
        for(int i=0; i < project.getListOfRefacingItems().size(); i++){
            if(project.getListOfRefacingItems().get(i).getId().equals(itemId)) {
                itemToDelete = project.getListOfRefacingItems().get(i);
            }
        }
        return itemToDelete;
    }

    private Transaction findTransaction(int transId, ProjectEstimate project) {
        Transaction result = null;
        for(int i=0; i < project.getListOfTransactions().size(); i++){
            if(project.getListOfTransactions().get(i).getId().equals(transId)) {
                result = project.getListOfTransactions().get(i);
            }
        }
        return result;
    }

    private ProjectItem findProjectItem(@PathVariable("itemId") int itemId, ProjectEstimate project) {
        ProjectItem itemToDelete = null;
        for(int i=0; i < project.getListOfProducts().size(); i++){
            if(project.getListOfProducts().get(i).getId().equals(itemId)) {
                itemToDelete = project.getListOfProducts().get(i);
            }
        }
        return itemToDelete;
    }

    @GetMapping(value = "/projects/{id}/refacing-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getRefacingPDF(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        try {
            byte[] contents = pdfGenerator.createRefacingItemsPdf(project);
            String filename = "refacing-" + System.currentTimeMillis() + ".pdf";
            return buildPdfResponse(contents, filename);
        } catch (Exception e) {
            return new ResponseEntity<>(new byte[]{},
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/projects/{id}/scheduling-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getSchedulingPDF(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        try {
            byte[] contents = pdfGenerator.createSchedulingItemsPdf(project);
            String filename = "scheduling-" + System.currentTimeMillis() + ".pdf";
            return buildPdfResponse(contents, filename);
        } catch (Exception e) {
            return new ResponseEntity<>(new byte[]{},
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/projects/{id}/quote-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getQuotePDF(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        try {
            byte[] contents = pdfGenerator.createQuoteItemsPdf(project);
            String filename = "quote-" + System.currentTimeMillis() + ".pdf";
            return buildPdfResponse(contents, filename);
        } catch (Exception e) {
            return new ResponseEntity<>(new byte[]{},
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<byte[]> buildPdfResponse(byte[] contents, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/projects/{id}/items")
    List<ProjectItem> getProductItems(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfProducts();
    }

    @GetMapping("/projects/{id}/items/sorted/{property}")
    List<ProjectItem> getProductItemsSorted(@PathVariable("id") int projectId, @PathVariable("property") String property) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();

        Comparator<? super ProjectItem> comparator = new Comparator<ProjectItem>() {
            @Override
            public int compare(ProjectItem o1, ProjectItem o2) {
                if(property.equals("id")) {
                    return o1.getId().compareTo(o2.getId());
                } else if(property.equals("material")) {
                    return o1.getProduct().getName().compareTo(o2.getProduct().getName());
                } else if(property.equals("category")) {
                    return o1.getProduct().getCategory().name().compareTo(o2.getProduct().getCategory().name());
                } else if(property.equals("cost")) {
                    return o1.getCostProjectPrice().compareTo(o2.getCostProjectPrice());
                } else if(property.equals("sell")) {
                    return o1.getSellProjectPrice().compareTo(o2.getSellProjectPrice());
                } else if(property.equals("paid")) {
                    Integer val1 = o1.isPaid() ? 1 :0;
                    Integer val2 = o2.isPaid() ? 1 :0;
                    return val1.compareTo(val2);
                }
                return 0;
            }
        };
        return project.getListOfProducts().stream()
                .sorted(comparator).collect(Collectors.toList());
    }

    @GetMapping("/projects/{id}/service-items")
    List<ProjectItem> getServiceItems(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfServices();
    }

    @GetMapping("/projects/{id}/refacing-items")
    List<RefacingItem> getRefacingItems(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfRefacingItems();
    }

    @GetMapping("/projects/{id}")
    ProjectEstimate getProject(@PathVariable("id") int projectId) {
        return projectEstimateService.findById(projectId).get();
    }

    @GetMapping("/projects")
    List<ProjectEstimate> getAllProject() {
        return projectEstimateService.findAll();
    }
}
