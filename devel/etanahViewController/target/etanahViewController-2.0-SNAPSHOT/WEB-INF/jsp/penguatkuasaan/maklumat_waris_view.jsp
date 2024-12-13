<%-- 
    Document   : maklumat_waris_view
    Created on : August 3, 2011
    Author     : siti.zainal
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function refreshPageWaris(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?reload';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }
    
    function viewWaris(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?viewWarisDetail&idWaris='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatWarisActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>
            <div class="content" align="center">
                <c:if test="${actionBean.multipleHakmilik eq false}">
                    <display:table class="tablecloth" name="${actionBean.listWaris}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="No.Pengenalan"><a class="popup" onclick="viewWaris(${line.idWarisOks})">${line.noPengenalan}</a></display:column>
                        <display:column property="nama" title="Nama"></display:column>
                        <display:column property="hubungan" title="Hubungan"></display:column>
                        <display:column title="Alamat">${line.alamat1}
                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                            ${line.alamat2}
                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                            ${line.alamat3}
                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                            ${line.alamat4}
                            ${line.poskod}
                            ${line.negeri.nama}
                        </display:column>
                    </display:table>
                </c:if>
                <c:if test="${actionBean.multipleHakmilik eq true}">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line" style="width:70%;">
                        <display:column title="Bil" style="width:3%;">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" style="width:5%;">
                            ${line.hakmilik.idHakmilik}
                        </display:column>
                        <display:column title="Maklumat Waris">
                            <c:set value="1" var="count"/>
                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Nama</b></th>
                                    <th  width="1%" align="center"><b>Alamat</b></th>
                                </tr>
                                <c:forEach items="${actionBean.listWaris}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <tr>
                                            <td width="5%">${count}</td>
                                            <td width="50%"><a class="popup" onclick="viewWaris(${senarai.idWarisOks})">${senarai.nama}</a></td>
                                            <td width="50%">
                                                ${senarai.alamat1}
                                                <c:if test="${senarai.alamat2 ne null}"> , </c:if>
                                                ${senarai.alamat2}
                                                <c:if test="${senarai.alamat3 ne null}"> , </c:if>
                                                ${senarai.alamat3}
                                                <c:if test="${senarai.alamat4 ne null}"> , </c:if>
                                                ${senarai.alamat4}
                                                ${senarai.poskod}
                                                <font style="text-transform: uppercase">${senarai.negeri.nama}</font>
                                            </td>
                                        </tr> 
                                    </c:if>


                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                        </display:column>
                    </display:table>
                </c:if>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>