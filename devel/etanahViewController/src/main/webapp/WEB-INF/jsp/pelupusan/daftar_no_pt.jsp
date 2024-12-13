<%--
    Document   : sedia_PU_NoPT
    Created on : Dec 30, 2009, 10:52:00 AM
    Author     : nursyazwani
--%>



<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%--<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
--%>
<script type="text/javascript">

    function janaNoPt(event, f,kodBpm){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&kodBPM='+kodBpm;
        $.post(url,q,
        function(data){
            $('#page_div',this.document).html(data);
        },'html');
    }

    function simpanJanaNoPt(event, f, kodBpm, index){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&kodBPM='+kodBpm+'&index='+index;
        $.post(url,q,
        function(data){
            $('#page_div',this.document).html(data);
        },'html');
    }


    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }



    <%--function addJanaNoPT(){
    var url = '${pageContext.request.contextPath}/pembangunan/noPT_noPU?janaPT';
    $.get(url,
    function(data){
    $('#page_div').html(data);
    },'html');
    }
    --%>

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.DaftarNoPtActionBean">

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jana No PT</legend>
            <div class="content" align="center">
                <c:set var="k" value="0" />
                <c:forEach items="${actionBean.senaraiNamaBpm}" var="line1">
                    <table width="30%" border="0">
                        <tr>
                            <td align="left" colspan="3"><b>KOD :</b>${actionBean.senaraiKodBpm[k]} <br>
                                <b>MUKIM :</b>${line1}</td>
                        </tr>

                    </table>
                    <br><br><br>
                    <table  class="tablecloth">
                        <tr>
                            <th><b>No. </b></th>
                            <%--<th><b>No Plot </b></th>--%>
                            <th><b>No. PT</b></th>
                           <%-- <th><b>Luas Diluluskan</b></th>--%>
                            <%--<th><b>Luas Diluluskan UOM</b></th>--%>
                        </tr>
                        <c:set var="i" value="0" />
                        <c:forEach items="${actionBean.senaraiNoPT[k]}" var="tbl1">
                            <tr>
                                <td><c:out value="${i+1}"/>. </td>
                                <%--<td>${tbl1.permohonanPlotPelan.noPlot}</td>--%>
                                <td>${tbl1.noPt} </td>
                                <%--<td> ${tbl1.luasDilulus}</td> <s:text name="luas${k}${i}" id="luas${k}${i}" onchange="calc();" value="${tbl1.luasDilulus}" onkeyup="validateNumber(this,this.value);"  style="width:130px;" formatPattern="#,##0.00"/>  formatPattern="#,##0.00"--%>
                                <%--<td> ${tbl1.kodUOM.nama} </td>--%>
                                <c:set var="i" value="${i+1}" />
                            </tr>
                        </c:forEach>
                    </table><br>
                    <c:if test="${empty actionBean.senaraiNoPT[k]}">
                        <s:button class="btn" value="Jana No PT" name="generatePT" id="janaPT" onclick="janaNoPt(this.name, this.form,'${actionBean.senaraiKodBpm[k]}');" />
                    </c:if>
                    <%--<s:button class="btn" value=" Simpan " name="simpan" id="simpan" onclick="simpanJanaNoPt(this.name, this.form,'${actionBean.senaraiKodBpm[k]}','${k}');" />--%>

                    <c:set var="k" value="${k+1}" />
                </c:forEach>
            </div>
        </fieldset>
    </div>
</s:form>


