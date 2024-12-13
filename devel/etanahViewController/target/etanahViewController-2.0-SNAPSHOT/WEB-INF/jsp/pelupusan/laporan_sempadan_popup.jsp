<%--
    Document   : laporan_sempadan_popup
    Created on : Dec 5, 2011, 12:07:01 PM
    Author     : Srinivas
    Modified   : Shazwan (25/12/2011 04:05:00 PM)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Agensi</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
     $(document).ready(function() {
         maximizeWindow();
         self.opener.refreshlptn();
  });

function reset1(){
    <%--alert("reset");--%>
    document.getElementById("namaPenyedia").value ="";
    document.getElementById("noRujukan").value ="";
    document.getElementById("ulasan").value ="";
    document.getElementById("syorJabatan1").value ="";
}

function validation(){
     if($("#syorJabatan1").val() == "0"){
            alert('Sila Pilih " SyorJabatan " .');
            $("#syorJabatan1").focus();
            return true;
    }
         if($("#ulasan").val() == ""){
            alert('Sila masukkan " ulasan " terlebih dahulu.');
            $("#ulasan").focus();
            return true;
    }
    return false;
}

//  function saveJabatanTeknikal(event, f){
//
//        var q = $(f).formSerialize();
//        hakmilik = document.getElementById("hakmilik").value;
//        var url = f.action + '?' + event + '&hakmilik_ref=' +hakmilik;
//        alert(url);
//        window.document.forms[0].action = url;
//        window.document.forms[0].submit();
//
//    }
    function saveSempadan(f){
//        alert('bbb');
          var idHakmilik = document.getElementById("hakmilik").value;
          var keadaanTanah = document.getElementById("keadaanTanah").value;
           var kegunaan = document.getElementById("kegunaan").value;
          var catatan =  document.getElementById("catatan").value;
          var milikKerajaan =  document.getElementById("milikKerajaan").value;
//          alert(idHakmilik);
          var index = document.getElementById("index").value;
//          alert('sss');
           var q = $(f).formSerialize();
          var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahTemplate?simpanKandunganSempadan&idHakmilik='+idHakmilik+'&index='+index
                    +'&kegunaan='+kegunaan+'&keadaanTanah='+keadaanTanah+'&catatan='+catatan+'&milikKerajaan='+milikKerajaan,q;
           $.get(url,
            function(data){
                $('#page_div').html(data);
                self.opener.refreshlptn();
            },'html');          
    }
        
    function closeWindow123(event, f){

        //self.close();

         var q = $(f).formSerialize();
         var url = f.action + '?' + event;
          $.post(url,q,
        function(data){
           $('#page_div',opener.document).html(data);
            self.close();
        },'html');

    }
    function resetUlasan(event, f){
        document.getElementById("hakmilik").value = '';
        document.getElementById("kegunaan").value = '';
        document.getElementById("keadaanTanah").value = '';
        document.getElementById("catatan").value = '';
        
       var q = $(f).formSerialize();
        var url = f.action + '?' + event;
       window.document.forms[0].action = url;
        window.document.forms[0].submit();


    }

    function changeUlasan(f, value){
        var q = $(f).formSerialize();
        var url = f.action + '?changeUlasan&ulasanValue='+value ;
         window.document.forms[0].action = url;
        window.document.forms[0].submit();

    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahTemplateActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="statusSempadan"/>
    <s:hidden name="laporanTanahSempadan.idLaporTanahSpdn" />
    <s:hidden name="idHakmilikSmpdn" />
    
    <div class="subtitle">
         <table>
                 <tr>
                    <td><label>Sempadan :</label></td>
                    <td>
                        <c:if test="${actionBean.statusSempadan eq null}">
                            <s:select name="index" id="index" >
                                <s:option value="U">Utara</s:option>
                                <s:option value="S">Selatan</s:option>
                                <s:option value="T">Timur</s:option>
                                <s:option value="B">Barat</s:option>
                                <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.statusSempadan ne null}">
                            <s:select name="index" id="index" value="${actionBean.jenisSmpdn}">
                                <s:option value="U">Utara</s:option>
                                <s:option value="S">Selatan</s:option>
                                <s:option value="T">Timur</s:option>
                                <s:option value="B">Barat</s:option>
                                <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                            </s:select>
                        </c:if>
                        </td>

                </tr>
                <tr>
                    <td><label>No. Hakmilik :</label></td>
                    <td>
                        <c:if test="${actionBean.statusSempadan eq null}">
                            <s:text name="idHakmilik" id="hakmilik"/>
                        </c:if>
                        <c:if test="${actionBean.statusSempadan ne null}">                            
                            ${actionBean.idHakmilikSmpdn}
                        </c:if>                        
                    </td>
                </tr>
                <tr>
                    <td><label>Kegunaan Tanah :</label></td>
                    <td>
                        <c:if test="${actionBean.statusSempadan eq null}">
                            <s:textarea name="kegunaan" id="kegunaan" rows="5" cols="80"></s:textarea>
                        </c:if>
                        <c:if test="${actionBean.statusSempadan ne null}">
                            <s:textarea name="kegunaan" id="kegunaan" rows="5" cols="80" value="${actionBean.kegunaanSmpdn}"></s:textarea>
                        </c:if>                        
                    </td>
                </tr>
                <tr>
                    <td><label>Keadaan Tanah :</label></td>
                    <td>
                        <c:if test="${actionBean.statusSempadan eq null}">
                            <s:textarea name="keadaanTanah" id="keadaanTanah" rows="5" cols="80"></s:textarea>
                        </c:if>
                        <c:if test="${actionBean.statusSempadan ne null}">
                            <s:textarea name="keadaanTanah" id="keadaanTanah" rows="5" cols="80" value="${actionBean.keadaanTanah}"></s:textarea>
                        </c:if>                        
                    </td>
                </tr>
                <tr>
                    <td> <label>Catatan :</label> </td>
                    <td>
                        <c:if test="${actionBean.statusSempadan eq null}">
                            <s:textarea name="catatan" id="catatan" rows="5" cols="80"></s:textarea>
                        </c:if>
                        <c:if test="${actionBean.statusSempadan ne null}">
                            <s:textarea name="catatan" id="catatan" rows="5" cols="80" value="${actionBean.catatan}"></s:textarea>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td><label>Milik :</label></td>
                    <td>
                        <c:if test="${actionBean.statusSempadan eq null}">
                            <s:select name="milikKerajaan" id="milikKerajaan" >
                                <s:option value="T">Milik</s:option>
                                <s:option value="Y">Kerajaan</s:option>
                                <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.statusSempadan ne null}">
                            <s:select name="milikKerajaan" id="milikKerajaan" value="${actionBean.milikKerajaanSmpdn}">
                                <s:option value="T">Milik</s:option>
                                <s:option value="Y">Kerajaan</s:option>
                                <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                            </s:select>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                    <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                    <c:if test="${actionBean.statusSempadan eq null}">
                        <s:submit name="simpanKandunganSempadan" value="Simpan" class="btn"/>
                        <s:button name="showEditSempadan" value="Isi Semula" class="btn" onclick="resetUlasan(this.name, this.form);" />
                    </c:if>
                    <c:if test="${actionBean.statusSempadan ne null}">
                        <s:submit name="simpanKandunganSempadan" value="Kemaskini" class="btn"/>
                    </c:if>
                     <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                     <%--<s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" />--%> 
                    </td>
                </tr>
            </table>
    </div>
</s:form>