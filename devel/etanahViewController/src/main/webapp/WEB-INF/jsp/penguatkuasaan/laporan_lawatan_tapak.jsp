<%-- 
    Document   : laporan_lawatan_tapak
    Created on : Jan 18, 2010, 2:43:10 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function validateForm(){

     if($("#tarikh").val()=="")
      {
          alert("Sila Isi Tarikh Lawatan");
          return false;
      }
      if($("#catatan").val()=="")
      {
          alert("Sila Isi catatan");
          return false;
      }
      
      return true;
}
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.penguatkuasaan.AduanSiasatanActionBean" name="form1">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Lawatan Tapak Dan Siasatan
            </legend>
       
            <div class="content">
                 <c:if test="${form}">
                <p>
                    <label>Tarikh Lawatan :</label>
                    <s:text name="tarikhSiasat" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikh"/>
                    <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                </p>
                <p>
                    <label>Tanah Pekan / Bandar / Desa :</label>
                    <s:radio name="aduanSiasatan.laporanTanah" value="Pekan"/>&nbsp;Pekan
                    <s:radio name="aduanSiasatan.laporanTanah" value="Bandar"/>&nbsp;Bandar
                    <s:radio name="aduanSiasatan.laporanTanah" value="Desa"/>&nbsp;Desa
                </p>
                <p>
                    <label>Lokasi Tanah :</label>
                    <s:textarea name="aduanSiasatan.laporanLokasi" rows="5" cols="50" />&nbsp;
                </p>
                <p>
                    <label>Keadaan Geografi Tanah :</label>
                    <s:textarea name="aduanSiasatan.laporanGeografi" rows="5" cols="50" />&nbsp;
                </p>
                <p>
                    <label>Jenis Pengusahaan :</label>
                   <s:textarea name="aduanSiasatan.aktiviti" rows="5" cols="50" />&nbsp;
                </p>

                <p>
                    <label>Jalan Keluar Masuk :</label>
                    <s:text name="aduanSiasatan.laporanJalan" size="53" />&nbsp;
                </p>
                <p>
                    <label>Kemudahan Asas :</label>
                    <s:textarea name="aduanSiasatan.kemudahanAsas" rows="5" cols="50" />&nbsp;
                </p>
                <p>
                    <label>Pembangunan kawasan sekeliling (lingkungan 1.0km) :</label>
                   <s:radio name="aduanSiasatan.adaPembangunan" id="aduanSiasatan.adaPembangunan" value="Y"/> Ada
                        <s:radio name="aduanSiasatan.adaPembangunan" id="aduanSiasatan.adaPembangunan" value="T"/> Tiada &nbsp;
                </p>
                <br>
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426'}">
                    <label>Catatan:</label>
                  </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426'}">

                        <label>Jenis Bahan Batuan yang Dipindah/Digali:</label></c:if>
                      <s:textarea name="aduanSiasatan.catatan" rows="5" cols="50" id="catatan"/>&nbsp;
                  
                </p>
                <br><br>
                <fieldset class="aras1">
                    <legend>
                       Tanah Sekeliling
                    </legend>
                    <div class="content" align="center">
                        <table class="tablecloth">

                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <s:radio name="aduanSiasatan.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="aduanSiasatan.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanUtaraNoLot" />
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanUtaraKegunaan" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <s:radio name="aduanSiasatan.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="aduanSiasatan.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanSelatanNoLot" />
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanSelatanKegunaan" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <s:radio name="aduanSiasatan.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="aduanSiasatan.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanTimurNoLot" />
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanTimurKegunaan" />
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <s:radio name="aduanSiasatan.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="aduanSiasatan.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanBaratNoLot" />
                            </td>
                            <td>
                                <s:text name="aduanSiasatan.sempadanBaratKegunaan" />
                            </td>
                        </tr>
                    </table></div>
                    <br>
                    
                </fieldset>

            </div>
        
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
        </p>
                 </c:if>
                 <c:if test="${view}">
                     <p>
                    <label>Tarikh Lawatan :</label>
                    <fmt:formatDate value="${actionBean.aduanSiasatan.tarikhSiasat}" pattern="dd/MM/yyyy" />&nbsp;
                </p>
                <p>
                    <label>Tanah Pekan / Bandar / Desa :</label>
                 ${actionBean.aduanSiasatan.laporanTanah}&nbsp;
                </p>
                <p>
                    <label>Lokasi Tanah :</label>
                    ${actionBean.aduanSiasatan.laporanLokasi}&nbsp;
                </p>
                <p>
                    <label>Keadaan Geografi Tanah :</label>
                    ${actionBean.aduanSiasatan.laporanGeografi}&nbsp;
                </p>
                <p>
                    <label>Jenis Perusahaan :</label>
                   ${actionBean.aduanSiasatan.aktiviti}&nbsp;
                </p>

                <p>
                    <label>Jalan Keluar Masuk :</label>
                    ${actionBean.aduanSiasatan.laporanJalan}&nbsp;
                </p>
                <p>
                    <label>Kemudahan Asas :</label>
                    ${actionBean.aduanSiasatan.kemudahanAsas}&nbsp;
                </p>
                <p>
                    <label>Pembangunan kawasan sekeliling (lingkungan 1.0km) :</label>
                    <c:if test="${actionBean.aduanSiasatan.adaPembangunan ne 'T'}">Ada</c:if>
                    <c:if test="${actionBean.aduanSiasatan.adaPembangunan eq 'T'}">Tiada</c:if>&nbsp;
                </p>
                <br>

                <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426'}">
                    <label>Jenis Bahan Batuan yang Dipindah/Digali:</label>
                    ${actionBean.aduanSiasatan.catatan}&nbsp;
                                
                  </c:if>
                 <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426'}">
                    <label>Catatan:</label>
                    ${actionBean.aduanSiasatan.catatan}&nbsp;
                  
                  </c:if>
                </p>
                <br><br>
                <fieldset class="aras1">
                    <legend>
                       Tanah Sekeliling
                    </legend>
                    <div class="content" align="center">
                        <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanUtaraMilikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanUtaraMilikKerajaan eq 'null'}"></c:if>
                                
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanUtaraNoLot}&nbsp;

                            </td>
                            <td>
                               ${actionBean.aduanSiasatan.sempadanUtaraKegunaan}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanSelatanMilikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanSelatanMilikKerajaan eq 'null'}"></c:if>
                                
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanSelatanNoLot}&nbsp;
                               
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanSelatanKegunaan}&nbsp;
                                
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanTimurMilikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanTimurMilikKerajaan eq 'null'}"></c:if>
                                </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanTimurNoLot}&nbsp;
                               
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanTimurKegunaan}&nbsp;
                                
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <c:if test="${actionBean.aduanSiasatan.sempadanBaratMilikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanBaratMilikKerajaan eq 'null'}"></c:if>
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanBaratNoLot}&nbsp;
                               
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanBaratKegunaan}&nbsp;
                               
                            </td>
                        </tr>
                    </table></div>
                    <br>
                </fieldset>
                </fieldset>
            </div>
                                </c:if>
</s:form>