
package com.gmail.hamed;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.data.provider.HierarchicalQuery;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.gmail.hamed.baseClass.kala;
import com.gmail.hamed.dbCon.DAOanbar;
import com.gmail.hamed.dbCon.DAOfrosh;
import com.gmail.hamed.dbCon.PDate;
import com.gmail.hamed.logic.LogicFactor;
import com.gmail.hamed.logic.LogicFrosh;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.Registration;
import com.vaadin.ui.Tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Routing
@Route("")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")

public class MainView extends VerticalLayout {
    int sum1=0;
    int sum2=0;
    HorizontalLayout hesabhor=new HorizontalLayout();
    HorizontalLayout newhorizontal=new HorizontalLayout();
    CheckboxGroup <String> craSize;
    HorizontalLayout hsearch;
    Button bLogin=new Button("Login");
    Button save;
    Button fsave;
    TreeGrid<kala> tableView=new TreeGrid<>();
    TextField ftname;
    TreeGrid<kala> tableView1=new TreeGrid<>();;
    HorizontalLayout fLayout;
    HorizontalLayout fLayout1;
  VerticalLayout formLayout;
    Tab tfrosh=new Tab("فروش");
    Tab tfaktor=new Tab("فاکتور");
    Tab tanbar=new Tab("انبار");
    Tab thesab=new Tab("صورت حساب");
    Tabs tmenu=new Tabs();
    TextField username=new TextField("Name");
    PasswordField userpass=new PasswordField("Password‍‍‍");
    boolean saveFactor=true;
    boolean saveFrosh=true;
    Label sumPrice=new Label();
    Label sumcunt=new Label();

    public MainView() throws SQLException {
        header();
        askUser();
    }

    private void header() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        addClassName("main-view");
        H1 h1=new H1("Lonidor");
        h1.addClassName("button");
        add(h1);
    }

    private void askUser() {
        bLogin.addClickListener(e->{if(username.getValue().equals("hamed")&&userpass.getValue().equals("1234")){
            remove(username,userpass,bLogin);

            try {
                menu();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            Notification.show("نام یا بسورد اشتباه است");
        }
        });
        add(username,userpass,bLogin);

    }

    private void menu() throws SQLException {
        System.out.println( getComponentCount());

        tmenu.add(tfrosh,tfaktor,tanbar,thesab);
        tmenu.setWidth("100%");

        tmenu.setFlexGrowForEnclosedTabs(1);


        tmenu.addSelectedChangeListener(e->{
            getChildren().forEach(i->{String s=i.getId().toString();
                if(s.equals("Optional[tableView]")){
                    tableView.removeAllColumns();
                remove(tableView);
                remove(hsearch);
                remove(newhorizontal);
            } if(s.equals("Optional[tableView1]")){
                    tableView1.removeAllColumns();
                remove(tableView1);
            }if(s.equals("Optional[fLayout]")){
                remove(fLayout1,fsave,fLayout,craSize);
            }if(s.equals("Optional[formLayout]"))
            remove(formLayout);
            });
            if(thesab.isSelected()){

                try {
                    tmenu.setSelectedTab(thesab);
                    hesab();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }else if(tfrosh.isSelected()){
                tmenu.setSelectedTab(tfrosh);

                frosh();

            }else if(tfaktor.isSelected()){


                tmenu.setSelectedTab(tfaktor);
                factor();

            }else if(tanbar.isSelected()){
                try {
                    tmenu.setSelectedTab(tanbar);
                    anbar();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(tmenu);
        frosh();
    }

    public void anbar() throws SQLException {
        hsearch = new HorizontalLayout();
        List<kala> kalaList = DAOanbar.selectAll();
        tableView.setHierarchyColumn("name");
        tableView.addColumn(kala::getCode).setHeader("کد");
        tableView.addColumn(kala::getNoe).setHeader("نوع");
        tableView.addColumn(kala::getName).setHeader("نام کالا");
        tableView.addColumn(kala::getColor).setHeader("رنگ");
        tableView.addColumn(kala::getSize).setHeader("سایز");
        tableView.addColumn(kala::getPriceIn).setHeader("قیمت خرید");
        tableView.addColumn(kala::getDateIn).setHeader("تاریخ ورود");
        tableView.addColumn(kala::getTolydi).setHeader("نام تولیدی");
        tableView.setItems(kalaList);
        tableView.setId("tableView");
        tableView.setHeight("1500px");
        Select<String> anbarsearch = new Select<>();
        anbarsearch.setLabel("فیلتر بر اساس");
        anbarsearch.setItems("نام تولیدی","تاریخ", "قیمت", "سایز", "رنگ", "نام کالا", "نوع", "کد");

        TextField searchFiled = new TextField("فیلد مورد نظر");

        Button shbutton=new Button("جستجو");


        for (kala p:
                kalaList){
            sum1+=p.getPriceIn();
        sum2++; }
        sumcunt.setText(""+sum2);
        sumPrice.setText(""+sum1);
        hsearch.add(anbarsearch,searchFiled,shbutton);
        shbutton.addClassName("button_search");
        shbutton.addClickListener(event -> {

            if(searchFiled.getValue().isEmpty()){
                tableView.setItems(kalaList);
                tableView.setItems(kalaList);
            }else{
        switch (anbarsearch.getValue()) {
            case "کد":
                tableView.setItems(kalaList);
                List<kala> lcode = (kalaList.stream().filter(e ->String.valueOf(e.getCode()).equals(searchFiled.getValue())).collect(Collectors.toList()));
               sum1=0;sum2=0;
                for (kala p:
                       lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
                sumcunt.setText(""+sum2);
                sumPrice.setText(""+sum1);
                    tableView.setItems(lcode);
                break;
            case "نوع":
                    tableView.setItems(kalaList);
                     lcode = (kalaList.stream().filter(e -> e.getNoe().equals(searchFiled.getValue())).collect(Collectors.toList()));
                    tableView.setItems(lcode);
                sum2=0;sum1=0;
                for (kala p:
                        lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
                sumcunt.setText(""+sum2+" تومان : ");
                sumPrice.setText(""+sum1+"تعداد کالا : ");
                break;
            case "نام کالا":
                    tableView.setItems(kalaList);
                    lcode = (kalaList.stream().filter(e -> e.getName().equals(searchFiled.getValue())).collect(Collectors.toList()));
                    tableView.setItems(lcode);
                sum2=0;sum1=0;
                for (kala p:
                        lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
                sumcunt.setText(""+sum2+" تومان : ");
                sumPrice.setText(""+sum1+"تعداد کالا : ");
                break;
            case "نام تولیدی":
                tableView.setItems(kalaList);
                lcode = (kalaList.stream().filter(e -> e.getTolydi().equals(searchFiled.getValue())).collect(Collectors.toList()));
                tableView.setItems(lcode);
                sum2=0;sum1=0;
                for (kala p:
                        lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
                sumcunt.setText(""+sum2+" تومان : ");
                sumPrice.setText(""+sum1+"تعداد کالا : ");
                break;
            case "رنگ":
                    tableView.setItems(kalaList);
                     lcode = (kalaList.stream().filter(e -> e.getColor().equals(searchFiled.getValue())).collect(Collectors.toList()));

                sum2=0;sum1=0;
                for (kala p:
                        lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
                sumcunt.setText(""+sum2+" تومان : ");
                sumPrice.setText(""+sum1+"تعداد کالا : ");
                break;
            case "سایز":
                    tableView.setItems(kalaList);
                    lcode = (kalaList.stream().filter(e -> String.valueOf(e.getSize()).equals(searchFiled.getValue())).collect(Collectors.toList()));
                    tableView.setItems(lcode);
                sum2=0;sum1=0;
                for (kala p:
                        lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
               sumcunt.setText(""+sum2+" تومان : ");
                sumPrice.setText(""+sum1+"تعداد کالا : ");
                break;
            case "قیمت":
                    tableView.setItems(kalaList);
                    lcode = (kalaList.stream().filter(e ->String.valueOf(e.getPriceIn()).equals(searchFiled.getValue())).collect(Collectors.toList()));
                    tableView.setItems(lcode);
                sum2=0;
                sum1=0;
                for (kala p:
                        lcode) {
                    sum2++;
                    sum1 += p.getPriceIn();
                }
                sumcunt.setText(""+sum2+" تومان : ");
                sumPrice.setText(""+sum1+"تعداد کالا : ");
                break;
            default:
        }}});
newhorizontal.add(sumPrice,sumcunt);
add(hsearch,tableView,newhorizontal);


    }

    public void hesab() throws SQLException {
        List<kala> kalaList1 = DAOfrosh.selectAll();
        NumberField firstyear=new NumberField("سال");
        NumberField firstmont=new NumberField("ماه");
        NumberField firstday=new NumberField("روز");
        
        tableView1.addColumn(kala::getCode).setHeader("کد");
        tableView1.addColumn(kala::getNoe).setHeader("نوع");
        tableView1.addColumn(kala::getName).setHeader("نام کالا");
        tableView1.addColumn(kala::getColor).setHeader("رنگ");
        tableView1.addColumn(kala::getSize).setHeader("سایز");
        tableView1.addColumn(kala::getPriceIn).setHeader("قیمت خرید");
        tableView1.addColumn(kala::getPriceOut).setHeader("قیمت فروش");
        tableView1.addColumn(kala::getDateIn).setHeader("تاریخ ورود");
        tableView1.addColumn(kala::getDateOut).setHeader("تاریخ خروج");
        tableView1.addColumn(kala::getTime).setHeader("زمان فروش");
        tableView1.setItems(kalaList1);
        tableView1.setId("tableView1");

        add(tableView1);
    }

    private void factor() {
        kala factor=new kala();
        fLayout=new HorizontalLayout();
        Select<String> cNoe = new Select<>();
        cNoe.setLabel("نوع");
        cNoe.setItems("شلوار", "روسری","مانتو","سایر");
        craSize =new CheckboxGroup<>();
        craSize.setLabel("سایز");
        craSize.setItems("free","36","38","40","42","44","46","48","50","52","54");
        Select<String> craRang = new Select<>();
        craRang.setLabel("رنگ");
        craRang.setItems("مشکی", "زرد","ابی","سبز","طوسی","قرمز","قهوه ای","صورتی","بادمجانی","کرمی","سایر");
        fLayout.add(craRang,cNoe);
        fLayout1=new HorizontalLayout();
          ftname=new TextField();ftname.setLabel("نام کالا");ftname.setWidth("105px");
         TextField fttname=new TextField();fttname.setLabel("نام تولیدی");fttname.setWidth("105px");
         NumberField ftnumber=new NumberField("کد");ftnumber.setWidth("55px");
        StringLengthValidator dsad=new StringLengthValidator("add integer numbers",3,10);
         NumberField ftPrice=new NumberField("قیمت");ftPrice.setWidth("85px");
        cNoe.addValueChangeListener(e->{
            if(cNoe.getValue().equals("روسری")){
                craSize.setEnabled(false);}
            else{
                craSize.setEnabled(true);}

    });
         fsave=new Button("ثبت",klick->{saveFactor=true;
             if(cNoe.isEmpty()) {
                 cNoe.setErrorMessage("Not Null");
                 cNoe.setInvalid(true);
                 saveFactor=false;
             }else if(cNoe.getValue().equals("روسری")||cNoe.getValue().equals("سایر")){
                if(ftnumber.isEmpty()){
                    saveFactor=false;
                    ftnumber.setErrorMessage("Not Null");
                    ftnumber.setInvalid(true);
                }if(ftPrice.isEmpty()){
                     saveFactor=false;
                     ftPrice.setErrorMessage("Not Null");
                     ftPrice.setInvalid(true);
                 }
             }else {
                 if (fttname.isEmpty()) {
                     saveFactor = false;
                     fttname.setErrorMessage("Not Null");
                     fttname.setInvalid(true);
                 }
                 if (ftname.isEmpty()) {
                     saveFactor = false;
                     ftname.setErrorMessage("Not Null");
                     ftname.setInvalid(true);
                 }
                 if (ftnumber.isEmpty()) {
                     saveFactor = false;
                     ftnumber.setErrorMessage("Not Null");
                     ftnumber.setInvalid(true);
                 }
                 if (ftPrice.isEmpty()) {
                     saveFactor = false;
                     ftPrice.setErrorMessage("Not Null");
                     ftPrice.setInvalid(true);
                 }

                 if (craRang.isEmpty()) {
                     saveFactor = false;
                     craRang.setErrorMessage("Not Null");
                     craRang.setInvalid(true);
                 }
             }
                 if(saveFactor){

             factor.setTime(PDate.pTime());
             factor.setDateIn(PDate.pDate());
             factor.setNoe(cNoe.getValue());
             factor.setCode(ftnumber.getValue().intValue());
             ArrayList<String> chekSize=new ArrayList<>(craSize.getValue());
             factor.setName(ftname.getValue());
             factor.setTolydi(fttname.getValue());
             factor.setPriceIn(ftPrice.getValue().intValue());
             factor.setColor(craRang.getValue());
             try {
                 kala res=LogicFactor.search(factor);
                 if(res!=null){
                     Dialog dialog = new Dialog();
                     dialog.add(new Label(res.toString()+"این کالا در انبار موجود است ایا مایل به ثبت ان هستید"));
                 System.out.println("sdvdsvd");
                     dialog.setWidth("400px");
                     dialog.setHeight("150px");
                     dialog.setCloseOnEsc(false);
                     dialog.setCloseOnOutsideClick(false);
                     Button confirmButton = new Button("Ok", event -> {
                         try {
                             LogicFactor.fLogic(factor,chekSize);
                         } catch (SQLException e) {
                             e.printStackTrace();
                         }
                         Notification.show("با موفقیت ثبت شد").addThemeName("green");
                         dialog.close();
                     });
                     Button cancelButton = new Button("Cancel", event -> {
                         dialog.close();
                     });
                     dialog.add(confirmButton, cancelButton);
                   dialog.open();
                 }else {
                 LogicFactor.fLogic(factor,chekSize);
              Notification.show("با موفقیت ثبت شد").addThemeName("green");
             }} catch (SQLException e) {
                 e.printStackTrace();
             }
         }});fsave.setWidth("55px");
        fsave.setClassName("button");
        fLayout1.add(fttname,ftname,ftnumber,ftPrice);
         fLayout.setId("fLayout");
         fLayout1.setId("fLayout1");
         fsave.setId("fsave");
        add(fLayout1,fLayout,craSize,fsave);

    }

    private void frosh() {
        kala fkala=new kala();

        formLayout=new VerticalLayout();
        formLayout.setAlignItems(Alignment.CENTER);
        formLayout.setSizeFull();
        Select<String> Noe = new Select<>();
         Noe.setLabel("نوع");
        Noe.setItems("شلوار", "روسری","مانتو","سایر");
        Select<String> raSize = new Select<>();
        raSize.setLabel("سایز");
        raSize.setItems("free","36","38","40","42","44","46","48","50","52","54");
        Select<String> raRang = new Select<>();
        raRang.setLabel("رنگ");
        raRang.setItems("مشکی", "زرد","ابی","سبز","طوسی","قرمز","قهوه ای","صورتی","بادمجانی","کرمی","سایر");
formLayout.add(Noe);
        TextField tname=new TextField();tname.setLabel("نام");
        NumberField tnumber=new NumberField("کد");
        NumberField tPrice=new NumberField("قیمت");
Noe.addValueChangeListener(e->{
if(Noe.getValue().equals("روسری")){
    raSize.setEnabled(false);
}else{
        raSize.setEnabled(true);}});
        save=new Button("ثبت",click->{saveFrosh=true;
            if(Noe.isEmpty()){
                Noe.setErrorMessage("Notnull");
                Noe.setInvalid(true);
                saveFrosh=false;
            }else{ if(Noe.getValue().equals("مانتو")||Noe.getValue().equals("شلوار")){
                if(raRang.isEmpty()){
                    raRang.setErrorMessage("not null");
                    raRang.setInvalid(true);
                    saveFrosh=false;
                }if(raSize.isEmpty()){
                    raSize.setErrorMessage("not null");
                    raSize.setInvalid(true);
                    saveFrosh=false;
                }

            }
                if(tname.isEmpty()&&tnumber.isEmpty()){
                    tname.setErrorMessage("Notnull");
                    tname.setInvalid(true);
                    tnumber.setErrorMessage("Notnull");
                    tnumber.setInvalid(true);
                    saveFrosh=false;
                }
            if(tPrice.isEmpty()){
                tPrice.setErrorMessage("Notnull");
                tPrice.setInvalid(true);
                saveFrosh=false;
            }}
            if(saveFrosh){
            fkala.setNoe(Noe.getValue());
            if(!tname.isEmpty())
            fkala.setName(tname.getValue());
            if(!tnumber.isEmpty())
            fkala.setCode(tnumber.getValue().intValue());
            if(!raSize.isEmpty())
            fkala.setSize(Integer.parseInt(raSize.getValue()));
            if(!raRang.isEmpty())
            fkala.setColor(raRang.getValue());
            fkala.setPriceOut(tPrice.getValue().intValue());
            fkala.setDateOut(PDate.pDate());
            fkala.setTime(PDate.pTime());
            try {
              kala frosh=LogicFrosh.searcher(fkala);
                if(frosh==null){
                    Dialog dialog = new Dialog();
                    dialog.add(new Label("این کالا در انبار موجود نیست ایا مایل به ثبت ان هستید"));
                    dialog.setWidth("400px");
                    dialog.setHeight("150px");
                    dialog.setCloseOnEsc(false);
                    dialog.setCloseOnOutsideClick(false);
                    Button confirmButton = new Button("Ok", event -> {
                        try {
                           DAOfrosh.insert(fkala);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Notification.show("با موفقیت ثبت شد").addThemeName("dark");
                        dialog.close();
                    });
                    Button cancelButton = new Button("Cancel", event -> {
                        dialog.close();
                    });
                    dialog.add(confirmButton, cancelButton);
                    dialog.open();
                }else {
                    frosh.setDateOut(PDate.pDate());
                    frosh.setTime(PDate.pTime());
                    frosh.setPriceOut(fkala.getPriceOut());
                    LogicFrosh.logicf(frosh);
                    Notification.show("با موفقیت ثبت شد");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }});save.setWidth("55px");
         save.setClassName("button");
        formLayout.add(tname,tnumber,raSize,raRang,tPrice,save);
formLayout.setId("formLayout");
        add(formLayout);
    }
}
