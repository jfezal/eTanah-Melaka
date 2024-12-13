<%-- 
    Document   : borang5A_BMBT
    Created on : Nov 3, 2012, 11:49:56 AM
    Author     : Afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

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

     function getHakmilikDetails(val){
        
        doBlockUI();
        var edit = $('#edit').val() ;
        var url = '${pageContext.request.contextPath}/pelupusan/borang5A_BMBT?searchHakmilik&idHakmilik='+val+'&edit='+edit;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
    
</script>
       <s:form beanclass="etanah.view.stripes.pelupusan.Borang5ABMBTActionBean" name="kira">
    <s:messages/>
    <div class="subtitle" align="center">
        <fieldset  class="aras1">
                <legend> 
                    Senarai Hakmilik Terlibat
                </legend> 
               
                <p>
                Hakmiilik :
                <s:select name="idHakmilik" id="idmohon" onchange="getHakmilikDetails(this.value)">
                       <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line">
                         <s:option value="${line.id}">${line.hakmilik.idHakmilik}(${line.hakmilik.bandarPekanMukim.daerah.nama}-${line.hakmilik.bandarPekanMukim.nama})</s:option>
                     </c:forEach> 
                </s:select>
                   </p>
               
        </fieldset>
        <fieldset class="aras1">
             <legend> 
                    Borang 5A
                </legend> 
            <table border="0">
                <tr>
                    <td>1:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><c:if test="${!item}">${actionBean.premium}</c:if>
                        <c:if test="${item}">${actionBean.premium}</c:if>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>RM <s:text name="premiumRM" onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                </tr>
                <c:if test="${actionBean.premiumT ne null}">
                    <tr>
                        <td>2:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.premiumT}</c:if>
                            <c:if test="${item}">${actionBean.premiumT}</c:if>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td>RM <s:text name="premiumTRM" onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td>3:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.pndftrn}</c:if>
                            <c:if test="${item}">${actionBean.pndftrn}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="pndftrnRM"  onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td>4:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.cukaiS}</c:if>
                            <c:if test="${item}">${actionBean.cukaiS}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="cukaiSRM"  onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td>5:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.ukur}</c:if>
                            <c:if test="${item}">${actionBean.ukur}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="ukurRM" onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <%--
                    <tr>
                        <td>6:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.tndaSmpdn}</c:if>
                            <c:if test="${item}">${actionBean.tndaSmpdn}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="tndaSmpdnRM" onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>--%>
                </c:if>
                <c:if test="${actionBean.premiumT eq null}">
                    <tr>
                        <td>2:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.pndftrn}</c:if>
                            <c:if test="${item}">${actionBean.pndftrn}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="pndftrnRM"  onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td>3:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.cukaiS}</c:if>
                            <c:if test="${item}">${actionBean.cukaiS}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="cukaiSRM"  onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td>4:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.ukur}</c:if>
                            <c:if test="${item}">${actionBean.ukur}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="ukurRM" onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    <%--
                    <tr>
                        <td>5:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><c:if test="${!item}">${actionBean.tndaSmpdn}</c:if>
                            <c:if test="${item}">${actionBean.tndaSmpdn}</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>RM <s:text name="tndaSmpdnRM" onkeyup="validateNumber(this,this.value);" style="width:80px;" readonly="true"/></td>
                    </tr>
                    --%>
                </c:if>    
                 
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JUMLAH</td>
                    <td>RM <s:text name="jumlah1" id="jumlah1" readonly="true" style="width:80px;" formatPattern="#,##0.00"/></td>
                </tr>
                
            </table><br><br>
        </fieldset>
    </div>

</s:form>
