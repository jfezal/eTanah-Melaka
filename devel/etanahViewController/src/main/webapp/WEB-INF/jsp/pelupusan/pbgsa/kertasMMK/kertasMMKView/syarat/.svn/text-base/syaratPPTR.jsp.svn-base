<%-- 
    Document   : syaratPPTR
    Created on : Oct 3, 2013, 2:11:46 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKV2ActionBean" name="form">
    <c:choose>
        <c:when test="${actionBean.kodNegeri eq '04'}">
            <table class="tablecloth" border="0">
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </td>
                </tr>
                <tr>
                    <td>Tempoh Pajakan:</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.tempohPajakan} Tahun
                    </td>
                </tr>                         
                <tr>
                    <td>Bayaran : </td>
                    <td>
                        RM <s:format value="${actionBean.amnt}" formatPattern="#,###,##0.00"/>
                    </td>
                </tr>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
            <table class="tablecloth" border="0">
                <tr>
                        <td>
                            Mukim :
                        </td>
                        <td>
                            ${actionBean.permohonan.cawangan.daerah.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tempat :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.lokasi}
                        </td>
                    </tr>
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </td>
                </tr>
                <tr>
                    <td>Tempoh Pajakan:</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.tempohPajakan} Tahun
                    </td>
                </tr>         
                    <tr>
                        <td>Kegunaan:</td>
                        <td>
                            ${actionBean.permohonan.sebab}
                        </td>
                    </tr>
                <tr>
                    <td>Kadar Bayaran : </td>
                    <td>
                        RM <s:format value="${actionBean.amnt}" formatPattern="#,###,##0.00"/> setahun
                    </td>
                </tr>
                <tr>
                        <td>Tempoh Tamat : </td>
                        <td>
                            Sehingga 21 tahun dari tarikh Suratcara pajakan didaftarkan.
                        </td>
                    </tr>
                    <tr>
                        <td>Syarat-syarat Tambahan : </td>
                        <td>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>i)</td>
                        
                        <td>
                           Pemajak hendaklah menjelaskan bayaran pajakan tahunan sebelum 31 Mei setiap tahun. Denda lewat sebanyak 5% daripada jumlah pajakan akan dikenakan
                           jika gagal berbuat demikian; 
                        </td>
                    </tr>
                    <tr>
                        <td >ii)</td>
                        <td>
                           Pajakan ini akan ditamatkan sekiranya berlaku kematian orang atau pembubaran badan yang buat masa itu berhak mendapat faedah darinya;
                        </td>
                    </tr>
                    <tr>
                        <td>iii)</td>
                        <td>
                           Pajakan ini boleh ditamatkan oleh Pihak Berkuasa Negeri dengan serta merta, atau pada bila-bila masa selepas, berlakunya pelanggaran mana-mana 
                           kaedah, terma atau syarat yang kepadanya pajakan itu adalah tertakluk tanpa bayaran apa-apa pampasan;
                        </td>
                    </tr>
                    <tr>
                        <td>iv)</td>
                        <td>
                           Pajakan ini boleh ditamatkan oleh Pihak Berkuasa Negeri di atas budi bicaranya pada bila-bila masa sebelum tarikh habis tempoh pajakan walaupun
                           pajakan itu belum lagi tertanggung kepada penamatan kerana pelanggaran mana-mana kaedah, terma atau syarat apabila bayaran apa-apa pampasan dibuat
                           sebagaiaman yang dipersetujui atau ditentukan di bawah Seksyen 434 Kanun Tanah Negara;
                        </td>
                    </tr>
                    <tr>
                        <td>v)</td>
                        <td>
                           Permit akan tamat pada 31 Disember tiap-tiap tahun dan mestilah diperbaharui dengan segera sebelum tahun berikut. tidak dibenarkan menjalankan 
                           sebarang kegiatan di atas tanah selain daripada yang disebutkan di atas;
                        </td>
                    </tr>
                    <tr>
                        <td>vi)</td>
                        <td>
                           Mematuhi syarat-syarat yang ditetapkan oleh Pentadbir Tanah ${actionBean.permohonan.cawangan.daerah.nama} dan jabatan-jabatan teknikal dari masa
                           ke semasa.
                        </td>
                    </tr>
                    <tr>
                        <td>vii)</td>
                        <td>
                           Perlu merujuk terlebih dahulu kepada Pentadbir Tanah untuk sebarang pembangunan tanah, pembinaan struktur dan aktiviti di atas tanah ini selain
                           dari yang dinyatakan di atas.
                        </td>
                    </tr>
            </table>     
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>


</s:form>
