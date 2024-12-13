<%--
    Document   : KemasukkanIdhahmilik_BantahanMahkamah
    Created on : May 31, 2011, 11:38:39 AM
    Author     : Rajesh
--%>

<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">

    function search(){
         // alert("search:"+index);
         var idH = $("#idHakmilik").val();
         var url = '${pageContext.request.contextPath}/pengambilan/kemasukanBantahanMahkamah?HakmilikPopup&idHakmilik='+idH;
         window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
     }

     function searchHakmilik(){
         // alert("search:"+index);
         var idH = $("#idHakmilik").val();
         if(idH == ""){
             alert('Sila masukkan Id Hakmilik');
             $("#idHakmilik").focus();
         }
         if(idH != null){
         var url = '${pageContext.request.contextPath}/pengambilan/kemasukanBantahanMahkamah?searchHakmilik&idHakmilik='+idH;
         $.get(url,
         function(data){
             $('#page_div').html(data);
         },'html');
         }
     }

    function selectMH(obj){
        $("#selectedIdHM").val(obj);
    }
    function selectRadio(obj){
        $("#selectedIdPihak").val(obj.value);
        $("#save").attr("disabled",false);
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
        $(update).html(data);
        $(update).show();
        });
        return false;
    }

</script>
<s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.KemasukkanIdhahmilik_BantahanMahkamahActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <%--<s:hidden name="hakmilikPermohonan.id" />--%>
        <s:hidden id="selectedIdSblm" name="selectedIdSblm" />
        <s:hidden id="selectedIdHakmilik" name="selectedIdHakmilik" />
        <s:hidden id="selectedIdPihak" name="selectedIdPihak" />
        <div class="subtitle">
            <fieldset class="aras1">
                <br/>
                <div >
                    <table>
                        <tr>
                            <td><label for="nama"><font color="red">*</font>Id Hakmilik :</label></td>
                            <td><s:text name="idHakmilik" id="idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                            <td>
                                <%--<s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/>--%>
                                <%--<s:button class="btn" value="Cari" name="add" onclick="javascript:searchHakmilik()"/>--%>
                                <s:submit class="btn" value="Cari" name="searchHakmilik" />
                            </td>
                        </tr>
                    </table>
                </div>
                            <br/><br/>
            </fieldset>
        </div>
        <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}" >
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Senarai Permohonan Beserta Nama Projek</legend><br />
                    <div  align="center">
                        <display:table style="width:70%" class="tablecloth" name="${actionBean.senaraiPermohonan}" pagesize="5" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanBantahanMahkamah" id="line">
                            <%--<display:column> <s:radio name="radio_" id="${line.id}" value="${line.id}" style="width:15px" onclick="javascript:selectMH(this)"/></display:column>--%>
                            <display:column  property="idPermohonan" title="Id Permohonan" style="vertical-align:top;"/>
                            <display:column title="Nama Projek" property="sebab" style="text-transform:uppercase;vertical-align:top;" />
                            <display:column title="Hakmilik" >
                                <display:table name="${line.senaraiHakmilik}" id="line1">
                                    <display:column title="IdHakmilik">
                                        <c:if test="${line1.hakmilik.idHakmilik eq actionBean.idHakmilik}">
                                        <s:link beanclass="etanah.view.stripes.pengambilan.KemasukkanIdhahmilik_BantahanMahkamahActionBean"
                                                event="selectHakmilik" >
                                            <s:param name="selectedIdSblm" value="${line.idPermohonan}"/>
                                            <s:param name="selectedIdHakmilik" value="${line1.hakmilik.idHakmilik}"/>
                                            <s:param name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                            ${line1.hakmilik.idHakmilik}
                                        </s:link>
                                    </c:if>
                                    </display:column>
                                </display:table>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
            <br/><br/>
            <c:if test="${actionBean.hakmilik ne null}" >
                <div  align="center">
                    <display:table style="width:70%" class="tablecloth" name="${actionBean.hakmilik}" pagesize="5" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanBantahanMahkamah" id="line">
                        <display:column  property="idHakmilik" title="Id Hakmilik" style="vertical-align:top;"/>
                        <display:column title="Tuan Tanah" >
                            <display:table name="${line.senaraiPihakBerkepentingan}" id="line1">
                                <display:column>
                                    <s:radio name="radio_" id="${line1.pihak.idPihak}" value="${line1.pihak.idPihak}" style="width:15px" onclick="javascript:selectRadio(this)"/>
                                </display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                            </display:table>
                        </display:column>
                    </display:table>
                    <s:submit name="simpan" id="save" value="Simpan" class="btn" disabled="true"/>
                </div>
            </c:if>
        </c:if>
            <br/>
      </div>
</s:form>

