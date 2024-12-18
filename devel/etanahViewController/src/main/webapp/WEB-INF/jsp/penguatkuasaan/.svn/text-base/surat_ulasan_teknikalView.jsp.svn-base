<%-- 
    Document   : surat_ulasan_teknikalView
    Created on : Apr 1, 2011, 10:46:34 AM
    Author     : Rohans
--%>


<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<head>


<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<script language="javascript" type="text/javascript"
              src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>

<script language="javascript" type="text/javascript"
              src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>

<script language="javascript" type="text/javascript"
				src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

<script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
     $(document).ready(function() {
         maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

  });

function reset1(){
    <%--alert("reset");--%>
    document.getElementById("namaPenyedia").value ="";
    document.getElementById("noRujukan").value ="";
    document.getElementById("ulasan").value ="";

}

function validation(){
    if($("#ulasan").val() == ""){
            alert('Sila masukkan " ulasan " terlebih dahulu.');
            $("#ulasan").focus();
            return true;
    }
    return false;
}

function saveJabatanTeknikal(event, f){
    if(validation()){

      }
      else{
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
      }
    }

</script>

</head>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<s:form beanclass="etanah.view.stripes.pelupusan.SuratUlasanTeknikalActionBean">--%>
<s:form beanclass="etanah.view.penguatkuasaan.JabatanTeknikal2ActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Penyediaan Laporan
            </legend>
            <table>
                <tr>
                    <td><label>Jabatan :</label>
                    <td>${actionBean.mohonRujLuar.namaAgensi} </td>
                </tr>
                <tr>
                    <td><label>Tarikh Penyediaan :</label></td>
                    <td><fmt:formatDate value="${actionBean.mohonRujLuar.tarikhRujukan}" pattern="dd/MM/yyyy"/>
                        <s:hidden name="mohonRujLuar.tarikhRujukan" />
                </tr>
                <tr>
                    <td> <label>Nama Penyedia :</label> </td>
                    <td>${actionBean.mohonRujLuar.namaPenyedia}</td>
                </tr>
                <tr>
                    <td><label>Nombor Rujukan :</label>
                    <td>${actionBean.mohonRujLuar.noRujukan}</td>
                </tr>
            </table>
       </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Jabatan Teknikal
            </legend>
            <table>
                <tr>
                    <td><label>Syor Jabatan :</label>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '1' }">
                    <td>Boleh Diluluskan</td>
                    </c:if>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '2' }">
                    <td>Tidak Diluluskan</td>
                    </c:if>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '3' }">
                    <td>Tidak Halangan</td>
                    </c:if>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '4' }">
                    <td>Sesuai</td>
                    </c:if>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '5' }">
                    <td>Tidak Sesuai</td>
                    </c:if>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '6' }">
                    <td>Sokong</td>
                    </c:if>
                    <c:if test="${actionBean.mohonRujLuar.diSokong eq  '7' }">
                    <td>Tidak Sokong</td>
                    </c:if>
                 
                </tr>


                <tr>
                    <td><label><font color="red">*</font>Ulasan :</label></td>
                    <td>${actionBean.mohonRujLuar.ulasan}</td>
                </tr>
                
            </table>
       </fieldset>
    </div>

</s:form>
