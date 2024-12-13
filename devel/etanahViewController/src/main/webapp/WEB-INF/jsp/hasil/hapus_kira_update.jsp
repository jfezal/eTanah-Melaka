<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>



<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.HapusKiraActionBean" id="hapus_kira">
    <script type="text/javascript">
        function cetak(f,id1){
        <%--alert("SINI");
        alert(f);
        alert(id1);--%>
        <%--var form = $(f).formSerialize();--%>
                var report = 'HSLLaporanHapusKira.rdf';
                

                var url = "reportName="+report+"%26report_p_id_dasar="+id1;
                

                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        <%--  window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+id1, "eTanah",
          "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
              }

              function clearText1(id) {

                  $("#" + id +" textarea").each( function(el) {
                      $(this).val('');
                  });

                  $("#"+id+" input:text").each( function(el) {
                      $(this).val('');
                  });

                  $("#" + id +" select").each( function(el) {
                      $(this).val('');
                  });

                  $("#"+id+" input:password").each( function(el) {
                      $(this).val('');
                  });
              }

              function validation() {

                  if($("#catathapus1").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus1").focus();
                      return false;
                  }
                  if($("#catathapus2").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus2").focus();
                      return false;
                  }
                  if($("#catathapus3").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus3").focus();
                      return false;
                  }
                  if($("#catathapus4").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus4").focus();
                      return false;
                  }
                  if($("#catathapus5").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus5").focus();
                      return false;
                  }
                  if($("#catathapus6").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus6").focus();
                      return false;
                  }
                  if($("#catathapus7").val() == ""){
                      alert('Sila masukkan " Catatan " terlebih dahulu.');
                      $("#catathapus7").focus();
                      return false;
                  }
                  if($("#pegawai1").val() == ""){
                      alert('Sila masukkan " Nama Pegawai " terlebih dahulu.');
                      $("#pegawai1").focus();
                      return false;
                  }
                  if($("#pegawai2").val() == ""){
                      alert('Sila masukkan " Nama Pegawai  " terlebih dahulu.');
                      $("#pegawai2").focus();
                      return false;
                  }
                  if($("#pegawai3").val() == ""){
                      alert('Sila masukkan " Nama Pegawai  " terlebih dahulu.');
                      $("#pegawai3").focus();
                      return false;
                  }
                  if($("#pegawai4").val() == ""){
                      alert('Sila masukkan " Nama Pegawai  " terlebih dahulu.');
                      $("#pegawai4").focus();
                      return false;
                  }
                  if($("#penyelia1").val() == ""){
                      alert('Sila masukkan " Nama Pegawai  " terlebih dahulu.');
                      $("#penyelia1").focus();
                      return false;
                  }
                  if($("#penyelia2").val() == ""){
                      alert('Sila masukkan " Nama Pegawai  " terlebih dahulu.');
                      $("#penyelia2").focus();
                      return false;
                  }

                  return true;
              }


    </script>

    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <%-- <div style="background-color: green;" align="center">
                         <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                             <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Hapus Kira</font>
                         </c:if>
                         <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                             <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Hapus Kira</font>
                         </c:if>
                     </div>--%>
                </td>
            </tr>
        </table></div>
        <%--<s:form beanclass="etanah.view.stripes.hasil.HapusKiraActionBean" id="hapus">--%>
        <s:messages/>
        <s:errors/>
        <%--<c:if test="${form1}">--%>
        <s:hidden name="form1" value="${form1}"/>
        <s:hidden name="radio"/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="50%">
                    <div class="content" align="center">
                        <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                <u><b>LAMPIRAN L</b></u><br /></td></tr>
                        <tr><td align="left"><b><label><font color="black">[ARAHAN PERBENDAHARAAN No. 238 (b)]</font></label></b></td></tr>
                        <tr><td> &nbsp;</td></tr>


                    </div>
                    <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><font style="text-transform: uppercase"><b>LAPORAN MENGENAI HUTANG DAN TUNGGAKAN HASIL YANG TIDAK BOLEH DIDAPATKAN</b></font><br /><br /><hr /><br /></td></tr>


                    <tr><td><b>1. Nama dan alamat orang yang kena membayar wang itu,</b></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>Seperti di Lampiran 'A'<td>

                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>2. Untuk apakah wang kena dibayar itu dan tarikh mula-mula ia kena dibayar kepada Kerajaan,</b></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>Seperti di Lampiran 'A'<td>

                            <tr><td> &nbsp;</td></tr>
                             <tr><td><b>3. Tarikh bil dihantar atau tarikh diberitahu tentang amaun yang genap masanya untuk didapatkan<em>*</em></b></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    a)
                                    <p>
                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus1" id='catathapus1' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>

                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>4. Apa tindakan yang telah diambil untuk mendapatkan bayaran semenjak bil dihantar atau bila diberitahu amaun yang genap masanya untuk didapatkan. Jika acara memberi notis dan mendapatkan wang itu ditetapkan dalam undang-undang, adakah acara tersebut dipatuhi,<em>*</em></b></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    a)
                                    <p>

                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus2" id='catathapus2' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    b)
                                    <p>

                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus3" id='catathapus3' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    c)
                                    <p>

                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus4" id='catathapus4' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    d)
                                    <p>

                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus5" id='catathapus5' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>


                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>5. Nama dan Jawatan Pegawai yang bertanggungjawab memungut wang itu<em>*</em></b></td></tr>
                            <tr><td> &nbsp;</td></tr>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    i)
                                    <p>
                                        <s:text name="dasarTuntutanCukaiHakmilik.pegawai1" id='pegawai1' onkeyup="this.value=this.value.toUpperCase();" style="height=30px;width=250px;"   />
                                    </p><td>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    ii)
                                    <p>

                                        <s:text name="dasarTuntutanCukaiHakmilik.pegawai2" id='pegawai2' onkeyup="this.value=this.value.toUpperCase();" style="height=30px;width=250px;"   />
                                    </p><td>

                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>6. Nama dan Jawatan Pegawai yang bertanggungjawab selaku penyelia<em>*</em></b></td></tr>
                            <tr><td> &nbsp;</td></tr>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    i)
                                    <p>

                                        <s:text name="dasarTuntutanCukaiHakmilik.pegawai3" id='pegawai3' onkeyup="this.value=this.value.toUpperCase();" style="height=30px;width=250px;"   />
                                    </p><td>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    ii)
                                    <p>

                                        <s:text name="dasarTuntutanCukaiHakmilik.pegawai4" id='pegawai4' onkeyup="this.value=this.value.toUpperCase();" style="height=30px;width=250px;"   />
                                    </p><td>

                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>7. Samada kegagalan memungut disebabkan oleh kegagalan mematuhi sesuatu peraturan atau arahan atau lain-lain sebab dipihak pegawai yang bertanggungjawab memungutnya,<em>*</em></b></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    a)
                                    <p>

                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus6" id='catathapus6' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>

                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>8. Samada tindakan tatatertib disyorkan terhadap sesorang pegawai. Jika tiada surcaj disyorkan sebab-sebab hendaklah diberikan,<em>*</em> </b></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    a)
                                    <p>

                                        <s:textarea name="dasarTuntutanCukaiHakmilik.catatanHapus7" id='catathapus7' onkeyup="this.value=this.value.toUpperCase();" cols="100" rows="5"  class="normal_text"/>
                                    </p><td>

                            <tr><td> &nbsp;</td></tr>
                            <tr><td><b>9. Dalam tiap-tiap satu hal dimana seorang pegawai disebut, nyatakan samada seorang Pegawai Negeri atau Persekutuan dalam perjawatan atau dalam perkhidmatan sementara<em>*</em></b></td></tr>
                            <tr><td> &nbsp;</td></tr>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    i)
                                    <p>

                                        <s:text name="dasarTuntutanCukaiHakmilik.penyelia1" id='penyelia1' onkeyup="this.value=this.value.toUpperCase();" style="height=30px;width=250px;"   />
                                    </p><td>

                            <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                                    ii)
                                    <p>

                                        <s:text name="dasarTuntutanCukaiHakmilik.penyelia2" id='penyelia2' onkeyup="this.value=this.value.toUpperCase();" style="height=30px;width=250px;"   />
                                    </p><td>


                </table>
            </div>
        </fieldset>
    </div>
    <p align="center">
        <s:submit name="simpanBaru" value="Simpan" class="btn" onclick="return validation();"/>
        <s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${actionBean.radio}')"/>
        <s:button name=" " value="Isi Semula" class="btn" onclick="clearText1('hapus_kira')"/>
        <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
    </p>
    <%--</c:if>--%>
</s:form>


