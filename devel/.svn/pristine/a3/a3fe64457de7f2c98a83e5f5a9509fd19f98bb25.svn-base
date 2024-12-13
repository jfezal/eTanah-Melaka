/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.etapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author faidzal
 */
public class EtappPesakaService {

//    public Hakmilik getHakmilikByNoresit(String noResit, String idHakmilik) {
//        EtappPermohonan service = new EtappPermohonan();
//        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
//
//        etanah.view.etapp.ws.Hakmilik hak = port.maklumatHakmilik(noResit, idHakmilik);
//
//        Hakmilik hakmilik = new Hakmilik();
//        hakmilik.setIdHakmilik(hak.getIdHakmilik().getValue());
//        hakmilik.setIdJenisHakmilik(hak.getIdJenisHakmilik().getValue());
//        hakmilik.setNoHakmilik(hak.getNoHakmilik().getValue());
//        hakmilik.setNoPT(hak.getNoPT().getValue());
//        hakmilik.setIdKategori(hak.getIdKategori().getValue());
//        //hakmilik.setIdJenisPB();
//        hakmilik.setIdNegeri(hak.getIdNegeri().getValue());
//        hakmilik.setIdDaerah(hak.getIdDaerah().getValue());
//        hakmilik.setIdMukim(hak.getIdMukim().getValue());
//        hakmilik.setIdLuas(hak.getIdLuas().getValue());
//        hakmilik.setLuas(hak.getLuas().getValue());
//        hakmilik.setCatatan(hak.getCatatan().getValue());
//        hakmilik.setStatusPemilikan(hak.getStatusPemilikan().getValue());
//        hakmilik.setTanggungan(hak.getTanggungan().getValue());
//        hakmilik.setJenisTanah(hak.getJenisTanah().getValue());
//        hakmilik.setNoPerserahan(hak.getNoPerserahan().getValue());
//        hakmilik.setSyaratNyata(hak.getSyaratNyata().getValue());
//        hakmilik.setSekatan(hak.getSekatan().getValue());
//        hakmilik.setKAVEAT(hak.getKAVEAT().getValue());
//        hakmilik.setNO_PERSERAHAN_KAVEAT(hak.getNOPERSERAHANKAVEAT().getValue());
//        hakmilik.setPAJAKAN(hak.getPAJAKAN().getValue());
//        hakmilik.setNO_PERSERAHAN_PAJAKAN(hak.getNOPERSERAHANPAJAKAN().getValue());
//        hakmilik.setTanggungan(hak.getTanggungan().getValue());
//        hakmilik.setNO_PERSERAHAN_GADAIAN(hak.getNOPERSERAHANGADAIAN().getValue());
//        for (etanah.view.etapp.ws.Pemilik p : hak.getListPemilik().getValue().getPemilik()) {
//            Pemilik pe = new Pemilik();
//            pe.setBA(p.getBA().getValue());
//            pe.setBB(p.getBB().getValue());
//            pe.setIdJenisPengenalan(p.getIdJenisPengenalan().getValue());
//            pe.setNamaPemilik(p.getNamaPemilik().getValue());
//            pe.setJenisPB(p.getJenisPB().getValue());
//            pe.setNoPengenalan(p.getNoPengenalan().getValue());
//            hakmilik.getListPemilik1().add(pe);
//        }
//
//        return hakmilik;
//
//    }
//
//    public StatusInfo insertMaklumatPerintah(Perintah perintah) throws DatatypeConfigurationException, ParseException {
//        StatusInfo s = new StatusInfo();
//        EtappPermohonan service = new EtappPermohonan();
//        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
//        ObjectFactory fact = new ObjectFactory();
//        PesakaDataSet1 pes = new PesakaDataSet1();
//
//        pes.setTmpBicara(fact.createPesakaDataSet1TmpBicara(perintah.getTempatBicara()));
//        pes.setAlamatBicara1(fact.createPesakaDataSet1AlamatBicara1(perintah.getAlamatBicara1()));
//        pes.setAlamatBicara2(fact.createPesakaDataSet1AlamatBicara2(perintah.getAlamatBicara2()));
//        pes.setAlamatBicara3(fact.createPesakaDataSet1AlamatBicara3(perintah.getAlamatBicara3()));
//        pes.setAlamatPemohon1(fact.createPesakaDataSet1AlamatPemohon1(perintah.getAlamatPemohon1()));
//        pes.setAlamatPemohon2(fact.createPesakaDataSet1AlamatPemohon2(perintah.getAlamatPemohon2()));
//        pes.setAlamatPemohon3(fact.createPesakaDataSet1AlamatPemohon3(perintah.getAlamatPemohon3()));
//        pes.setNamaPemohon(fact.createPesakaDataSet1NamaPemohon(perintah.getNamaPemohon()));
//        pes.setNamasimati(fact.createPesakaDataSet1Namasimati(perintah.getNamaSimati()));
//        pes.setNegeriBicara(fact.createPesakaDataSet1NegeriBicara(perintah.getNegeriBicara()));
//        pes.setNoFail(fact.createPesakaDataSet1NoFail(perintah.getNoFail()));
//        pes.setNoKpPemohon(fact.createPesakaDataSet1NoKpPemohon(perintah.getNoKPPemohon()));
//        pes.setNoKpSimati(fact.createPesakaDataSet1NoKpSimati(perintah.getNoKPSimati()));
//        pes.setNoSijilMati(fact.createPesakaDataSet1NoSijilMati(perintah.getNoSijilMati()));
//        pes.setPegawaiBicara(fact.createPesakaDataSet1PegawaiBicara(perintah.getPegawaiBicara()));
//        pes.setPoskodBicara(fact.createPesakaDataSet1PoskodBicara(perintah.getPoskodBicara()));
//        pes.setPoskodPemohon(fact.createPesakaDataSet1PoskodPemohon(perintah.getPoskodPemohon()));
//        pes.setTarikhMati(setXMLGregorianCalendar(perintah.getTarikhMati()));
//        pes.setTarikhPerintah(setXMLGregorianCalendar(perintah.getTarikhPerintah()));
//        pes.setTmpBicara(fact.createPesakaDataSet1TmpBicara(perintah.getTempatBicara()));
//        ArrayOfHakmilikDataSet ahd = new ArrayOfHakmilikDataSet();
//        for (int i = 0; i < perintah.getListHakmilik().length; i++) {
//
//            HakmilikDataSet hakmilikdata = new HakmilikDataSet();
//            HakmilikPerintah hp = perintah.getListHakmilik()[i];
//            hakmilikdata.setBasimati(fact.createHakmilikDataSetBasimati(hp.getBASimati()));
//            hakmilikdata.setBbsimati(fact.createHakmilikDataSetBbsimati(hp.getBBSimati()));
//            hakmilikdata.setIdHakmilik(fact.createHakmilikDataSetIdHakmilik(hp.getIdHakmilik()));
//            hakmilikdata.setJenisHakmilik(fact.createHakmilikDataSetJenisHakmilik(hp.getIdJenisHakmilik()));
//            hakmilikdata.setKodLuas(fact.createHakmilikDataSetKodLuas(hp.getIdLuas()));
//            hakmilikdata.setKodPembahagian(fact.createHakmilikDataSetKodPembahagian(hp.getJenisPembahagian()));
////            hakmilikdata.setLuas(fact.createHakmilikDataSetLuas(Integer.parseInt(hp.getLuas())));
//            hakmilikdata.setNoHakmilik(fact.createHakmilikDataSetNoHakmilik(hp.getNoHakmilik()));
//
//            if (hp.getListBorangE() != null) {
//                ArrayOfBorangE aob = fact.createArrayOfBorangE();
//                for (int q = 0; q < hp.getListBorangE().length; q++) {
//                    BorangE be = hp.getListBorangE()[q];
//                    etanah.view.etapp.ws.BorangE boe = new etanah.view.etapp.ws.BorangE();
//                    boe.setBasimati(fact.createBorangEBasimati(be.getBA()));
//                    boe.setBbsimati(fact.createBorangEBbsimati(be.getBB()));
//                    boe.setNamaWaris(fact.createBorangENamaWaris(be.getNamaOB()));
//                    boe.setNoKpWaris(fact.createBorangENoKpWaris(be.getNoKPOB()));
//                    boe.setAlamat1(fact.createBorangEAlamat1(be.getAlamat1()));
//                    boe.setAlamat2(fact.createBorangEAlamat2(be.getAlamat2()));
//                    boe.setAlamat3(fact.createBorangEAlamat3(be.getAlamat3()));
//                    boe.setPoskod(fact.createBorangEPoskod(be.getPoskod()));
//                    boe.setBandar(fact.createBorangEBandar(be.getBandar()));
//                    boe.setNegeri(fact.createBorangENegeri(be.getNegeri()));
//                    boe.setJantina(fact.createBorangEJantina(be.getJantina()));
//                    boe.setJenisPengenalan(fact.createBorangEJenisPengenalan(be.getJenisPengenalan()));
//                    //boe.setJenisPihakKepentingan(fact.createBorangEJenisPihakKepentingan(be.getj));
//                    boe.setKewarganaan(fact.createBorangEKewarganaan(be.getKewarganegaraan()));
//                    boe.setStatusPerkahwinan(fact.createBorangEStatusPerkahwinan(be.getStatusPerkahwinan()));
//                    boe.setTrhLahir(fact.createBorangETrhLahir(be.getTarikhLahir()));
//
//                    if (be.getListBorangH() != null) {
//                        ArrayOfBorangH abo = new ArrayOfBorangH();
//                        for (int h = 0; h < be.getListBorangH().length; h++) {
//                            BorangH bh;
//                            bh = hp.getListBorangE()[q].getListBorangH()[h];
//                            etanah.view.etapp.ws.BorangH boh = new etanah.view.etapp.ws.BorangH();
//                            boh.setNamaWaris(fact.createBorangHNamaWaris(bh.getNamaOB()));
//                            boh.setNoKpWaris(fact.createBorangHNoKpWaris(bh.getNoKPOB()));
//                            boh.setNoKpWaris(fact.createBorangENoKpWaris(bh.getNoKPOB()));
//                            boh.setAlamat1(fact.createBorangHAlamat1(bh.getAlamat1()));
//                            boh.setAlamat2(fact.createBorangHAlamat2(bh.getAlamat2()));
//                            boh.setAlamat3(fact.createBorangHAlamat3(bh.getAlamat3()));
//                            boh.setPoskod(fact.createBorangHPoskod(bh.getPoskod()));
//                            boh.setBandar(fact.createBorangHBandar(bh.getBandar()));
//                            boh.setNegeri(fact.createBorangHNegeri(bh.getNegeri()));
//                            boh.setJantina(fact.createBorangHJantina(bh.getJantina()));
//                            boh.setJenisPengenalan(fact.createBorangHJenisPengenalan(bh.getJenisPengenalan()));
//                            //boe.setJenisPihakKepentingan(fact.createBorangHJenisPihakKepentingan(be.getj));
//                            boh.setKewarganaan(fact.createBorangHKewarganaan(bh.getKewarganegaraan()));
//                            boh.setStatusPerkahwinan(fact.createBorangHStatusPerkahwinan(bh.getStatusPerkahwinan()));
//                            boh.setTrhLahir(fact.createBorangETrhLahir(bh.getTarikhLahir()));
//                            abo.getBorangH().add(boh);
////                           
//                        }
//                        boe.setListEtappBorangH(fact.createBorangEListEtappBorangH(abo));
//
//                    }
//                    aob.getBorangE().add(boe);
//                    hakmilikdata.setListEtappBorangE(fact.createHakmilikDataSetListEtappBorangE(aob));
//
//                }
//            }
//            if (hp.getListBorangF() != null) {
//                ArrayOfBorangF aoF = fact.createArrayOfBorangF();
//                for (int w = 0; w < hp.getListBorangF().length; w++) {
//                    BorangF bf = hp.getListBorangF()[w];
//                    etanah.view.etapp.ws.BorangF bof = new etanah.view.etapp.ws.BorangF();
//
//                    bof.setNamaWaris(fact.createBorangENamaWaris(bf.getNamaOB()));
//                    bof.setNoKpWaris(fact.createBorangENoKpWaris(bf.getNoKPOB()));
//                    bof.setAlamat1(fact.createBorangFAlamat1(bf.getAlamat1()));
//                    bof.setAlamat2(fact.createBorangFAlamat2(bf.getAlamat2()));
//                    bof.setAlamat3(fact.createBorangFAlamat3(bf.getAlamat3()));
//                    bof.setPoskod(fact.createBorangFPoskod(bf.getPoskod()));
//                    bof.setBandar(fact.createBorangFBandar(bf.getBandar()));
//                    bof.setNegeri(fact.createBorangFNegeri(bf.getNegeri()));
//                    bof.setJantina(fact.createBorangFJantina(bf.getJantina()));
//                    bof.setJenisPengenalan(fact.createBorangFJenisPengenalan(bf.getJenisPengenalan()));
//                    //bof.setJenisPihakKepentingan(fact.createBorangFJenisPihakKepentingan(be.getj));
//                    bof.setKewarganaan(fact.createBorangFKewarganaan(bf.getKewarganegaraan()));
//                    bof.setStatusPerkahwinan(fact.createBorangFStatusPerkahwinan(bf.getStatusPerkahwinan()));
//                    bof.setTrhLahir(fact.createBorangFTrhLahir(bf.getTarikhLahir()));
//
//                    aoF.getBorangF().add(bof);
//                    hakmilikdata.setListEtappBorangF(fact.createHakmilikDataSetListEtappBorangF(aoF));
//
//                }
//            }
//
//            ahd.getHakmilikDataSet().add(hakmilikdata);
//            pes.setHakmilikDataSet(fact.createPesakaDataSet1HakmilikDataSet(ahd));
//        }
//        port.terimaPerintah(pes);
//        return s;
//    }

    public XMLGregorianCalendar setXMLGregorianCalendar(String yourDate) throws DatatypeConfigurationException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dd = sdf.parse(yourDate);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dd);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return date2;
    }
}
