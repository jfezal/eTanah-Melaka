package etanah.etapp.etappclient;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package etanah.etapp.etappclient;
//
//import ws.HakmilikServiceStub;
//import ws.HakmilikServiceStub.Hakmilik;
//
///**
// *
// * @author faidzal
// */
//public class HartaPersekutuanService {
//
//    public StatusInfo insertHakmilik(Hakmilik hakmilik) {
//        StatusInfo sts = new StatusInfo();
//        sts.setMethod("insertHakmilik");
//        try {
//            HakmilikServiceStub stub = new HakmilikServiceStub();
//            HakmilikServiceStub.InsertHakmilik hak = new HakmilikServiceStub.InsertHakmilik();
//            Hakmilik param = new HakmilikServiceStub.Hakmilik();
//            hak.setHakmilik(param);
//            stub.insertHakmilik(hak);
//        } catch (Exception ex) {
//            sts.setMsg(ex.toString());
//
//        }
//        return sts;
//    }
//}
