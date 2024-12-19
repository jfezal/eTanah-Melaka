<%-- 
    Document   : maklumat_hakmilik
    Created on : Mar 12, 2013, 3:10:07 PM
    Author     : haqqiem
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function edit(id) {
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
    
    function reloadEdit (id) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/hasil/maklumat_hakmilik?reloadEdit&idHakmilik=' + id;
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

<s:form beanclass="etanah.view.stripes.hasil.MaklumatHakmilikActionBean" id="maklumat_hakmilik">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <div align="center">          
                <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">Hakmilik :</font>
                <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:450px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </div>
            <br/>
        </fieldset>
        <br/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <c:if test="${actionBean.negeri eq 'melaka'}">
                <p>
                    <label>No. Akaun :</label>
                    ${actionBean.hakmilik.akaunCukai.noAkaun}&nbsp;
                </p>
            </c:if>            
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}&nbsp;
            </p>
            <p>
                <label>ID Kumpulan :</label>
                <c:if test="${actionBean.hakmilik.akaunCukai.kumpulan ne null}">
                    ${actionBean.hakmilik.akaunCukai.kumpulan.idKumpulan}&nbsp;
                </c:if>
                <c:if test="${actionBean.hakmilik.akaunCukai.kumpulan eq null}">
                    Tiada.&nbsp;
                </c:if>
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.hakmilik.daerah.nama}&nbsp;
            </p>
            <p>
                <label>Bandar / Pekan / Mukim :</label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Lot :</label>
                ${actionBean.hakmilik.noLot}&nbsp;
            </p>
            <p>
                <label>Luas :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}" />&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}
            </p>
            <p>
                <label>Cukai Tanah (RM) :</label>
                <s:format formatPattern="#,##0.00" value="${actionBean.hakmilik.cukaiSebenar}" />&nbsp;
            </p>
            <p>
                <label>Kategori Tanah :</label>
                ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <a href="#" onclick="edit('${actionBean.hakmilik.syaratNyata.kod}');
        return false;">${actionBean.hakmilik.syaratNyata.kod}</a>&nbsp;
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
