<%-- 
    Document   : carianPembetulan
    Created on : Apr 9, 2010, 6:01:40 PM
    Author     : mohd.fairul
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#idPerserahan input:text").each( function(el) {
            $(this).blur( function() {
                $(this).val( $(this).val().toUpperCase());
            });
        });
    });

    function clickclear() {
    form1.idHakmilik.value = "";
    form1.idPerserahan.value = "";
   
}

     <%--    function find(idP,idH){
            alert(idP+idH);
             var url = '${pageContext.request.contextPath}/daftar/carianBU?carianBetul&idPerserahan='+idP+"&idHakmilik="+idH;

           $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
         }--%>

</script>
<s:errors/>
<s:messages/>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Carian Pembetulan
        </legend>
        <div class="content" align="center">

            <s:form beanclass="etanah.view.stripes.nota.CarianPembetulanActionBean" name="form1">
                <table>
                    <tr>
                        <td class="rowlabel1">ID Perserahan :</td>
                        <td class="input1"><s:text name="idPerserahan"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td ><font color="red">ATAU</font></td>
                    </tr>
                   <tr>
                        <td class="rowlabel1">ID Hakmilik :</td>
                        <td class="input1"><s:text name="idHakmilik"/> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <br>
                             <s:submit name="carianBetul" value="Cari" class="btn"/>
                            <s:button  name="reset" value="Isi Semula" class="btn" onclick="clickclear();"/>
                        </td>
                    </tr>
                </table>
            </s:form>
            <br>

        </div>
    </fieldset>
</div>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Senarai Pembetulan
        </legend>


        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.hakmilikUrusanList}"  id="line" pagesize="10" style="width:95%"
                           requestURI="${pageContext.request.contextPath}/daftar/carianBU">
               <display:column title="Bil" class="bil${line_rowNum}">${line_rowNum}</display:column>
               <display:column title="ID Perserahan">
               <a href="${pageContext.request.contextPath}/daftar/carianBU?detail&idPerserahan=${line.idPerserahan}">${line.idPerserahan}</a>
               </display:column>
               <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
               <display:column property="kodUrusan.nama" title="Urusan"/>
                
                
            </display:table>
        </div>
    </fieldset>

</div>
<br>
