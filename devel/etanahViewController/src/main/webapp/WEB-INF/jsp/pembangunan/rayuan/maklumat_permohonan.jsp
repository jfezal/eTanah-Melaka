<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

    $(document).ready(function() {
        var idMohonCarianSebelum;
        $("#catatanRayuan").val('');
//       var mhSblm = document.getElementById("idMohonSblm").value;//$("#idMohonSblm").val();
//        $("#catatanMH").hide();
//        
        $("#search").click(function()
        {
            idMohonCarianSebelum = $("#idMHSblm").val();
            $("#idMohonCarian").val(idMohonCarianSebelum);
           // alert($("#idMohonCarian").val());
        });
        

     });
     
      
     
        function deleteRayuan1(frm,idRayuan){
            alert(frm+"rayuan:"+idRayuan);
           var url = '${pageContext.request.contextPath}/rayuan/maklumatPermohonanRayuan?deleteRayuan&idRayuan='+idRayuan;
           if(confirm("Adakah anda pasti untuk hapus dokumen?")){
               frm.action = url;
           frm.submit();
           }
        }
        
        function deleteRayuan(frm,idRayuan){
            if(confirm('Adakah pasti untuk hapus dokumen?')){
                var url = '${pageContext.request.contextPath}/rayuan/maklumatPermohonanRayuan?deleteRayuan&idRayuan='+idRayuan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

    function kemaskiniCatatanBaru() {
       // alert(" = idMohonHakmilik");
        window.open("${pageContext.request.contextPath}/rayuan/maklumatPermohonanRayuan?kemaskiniUlasanPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pembangunan.MaklumatPermohonanRayuanActionBean" name ="rayuan" id ="rayuan">

    <s:hidden name="idMohonSblm" id="idMohonSblm" value="${actionBean.permohonanSblm}"/>
    <s:errors/>
    <s:messages/>

    <div id="test" class="subtitle">
        <fieldset class="aras1">
            <%--<c:if test="${actionBean.popap eq false}">--%>
                <legend>Maklumat Permohonan</legend>
                <p>
                    <label for="Permohonan">ID Permohonan Sebelum :</label>
                    <s:text name="idMHSblm" id="idMHSblm" size="20" class="normal_text" value=""/>
                    <s:button name="search" value="Cari" class="btn" id="search" onclick="doSubmit(this.form, this.name, 'page_div')" />
                </p>

            </fieldset>

            <%--<c:if test="${actionBean.permohonanSblm ne null}">--%>
                <fieldset id="MakHM" class="aras1">
                    <legend>Maklumat Hakmilik</legend>
                      <c:if test="${actionBean.tambahHakmilik}">
                        <p><s:button name="saveHakmilik" value="Tambah" class="btn" id="saveH" onclick="kemaskiniCatatanBaru();" /></p>
                    </c:if>
                    <display:table class="tablecloth" name="${actionBean.listhakHakmilikPermohonan1}" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                        <display:column property="permohonan.permohonanSebelum.idPermohonan" title="ID Mohon" />
                        <display:column property="permohonan.kodUrusan.nama" title="Nama Permohonan" />
                        <display:column property="luasTerlibat" title="Luas" /> <display:column property="kodUnitLuas.nama" title="Unit Luas" />
                       <%-- <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="kemaskiniCatatanBaru('${line.id}');" />
                            </div>
                        </display:column>   --%> 

                    </display:table>

                    &nbsp;&nbsp;
               </fieldset>
                
              <fieldset id="rayuanOld" class="aras1">
                  <legend>
                      Maklumat Permohonan Rayuan Terdahulu
                      <c:if test="${actionBean.idMHSblm ne null}">
                          bagi ID Permohonan ${actionBean.idMHSblm}
                      </c:if>
                  </legend>
                
                 <display:table class="tablecloth" name="${actionBean.listRY}" cellpadding="0" cellspacing="0" id="line">
                         <display:column property="permohonan.idPermohonan" title="ID Permohonan" />
                        <display:column property="jenisRayuan" title="Jenis Rayuan" />
                        <display:column property="noRujFilePTD" title="No Fail Rujukan Daerah" />
                        <display:column property="noRujFilePTG" title="No Fail Rujukan PTG" /> 
                        <display:column property="bilRayuan" title="Rayuan Ke Berapa" />
                        <display:column property="catatan" title="Catatan" />
                 </display:table>
            </fieldset>
             <%-- </c:if>--%>
       <%-- </c:if>--%>
            <%--       <c:if test="${actionBean.popap eq true}"> --%>
           <%-- <c:if test="${actionBean.popap eq true}">--%>
            <fieldset id="catatanMH" class="aras1">
                <legend>Maklumat Permohonan Rayuan</legend>
                
                 <display:table class="tablecloth" name="${actionBean.listPermohonanRayuan}" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="jenisRayuan" title="Jenis Rayuan" />
                        <display:column property="noRujFilePTD" title="No Fail Rujukan Daerah" />
                        <display:column property="noRujFilePTG" title="No Fail Rujukan PTG" /> 
                        <display:column property="bilRayuan" title="Rayuan Ke Berapa" />
                        <display:column property="catatan" title="Catatan" />
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="deleteRayuan(document.forms.rayuan,'${line.idRayuan}');" />
                            </div>
                        </display:column> 

                    </display:table>
            </fieldset>
           
            <fieldset id="catatanMH" class="aras1">
                <legend>Maklumat Catatan Rayuan</legend>
                <p> 
                    <label>Jenis Rayuan :</label>
                    <s:text name="JnsRayuan" id="JnsRayuan" size="25" class="normal_text" value=""/>
                </p>
                <p> 
                    <label>No Fail Rujukan Daerah :</label>
                    <s:text name="rujDaerah" id="rujDaerah" size="30" class="normal_text" value=""/>
                </p>
                <p> 
                    <label>No Fail Rujukan PTG :</label>
                    <s:text name="rujPTG" id="rujPTG" size="30" class="normal_text" value=""/>
                </p>
                <p> 
                    <label>Rayuan Ke Berapa :</label>
                    <s:text name="bilRayuan" id="bilRayuan" size="5" class="normal_text" value=""/>
                </p>
                <p> 
                    <label>Catatan :</label>
                    <s:textarea cols="80" id="catatanRayuan" name="catatan"  rows="5"/>
                    
                </p>
                <%-- <c:if test="${actionBean.tambahHakmilik}">--%>
                    <p align="center">
                        <s:button name="saveRayuan" value="Simpan" class="btn" id="save1" onclick="doSubmit(this.form, this.name, 'page_div')" />
                    </p>
                    <%--</c:if>--%>
            </fieldset>
            <%--</c:if>--%>
        <%--  </c:if>--%>


    </div>


</s:form>
