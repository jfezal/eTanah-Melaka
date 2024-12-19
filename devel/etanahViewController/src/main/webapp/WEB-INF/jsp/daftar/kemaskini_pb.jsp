
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

   function doGetContent(id) {
       if (id == '') {
           $('#div_content').html('');
       } else{
           $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });

       var url = '${pageContext.request.contextPath}/pihak_berkepentingan?doGetPartialPage&idHakmilik=' + id;
       $.ajax({
           url : url,
           dataType : 'html',
           success : function(data) {$('#div_content').html(data);$.unblockUI();}
       });

       }
   }
</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
        <legend>Senarai Hakmilik</legend>
        <br/>
        &nbsp;&nbsp;<font color="black" size="2">Sila pilih hakmilik untuk membuat kemasukan. Hanya tuan tanah yang sama akan dipaparkan.</font>
        <div class="content">
            <p>
                <label>Hakmilik :</label>
                <s:select name="" onchange="doGetContent(this.value);">
                <%--    <s:options-collection collection="${actionBean.p.senaraiHakmilik}"
                                          value="${hakmilik.idHakmilik}" label="${hakmilik.idHakmilik}"/>--%>
                        <s:option value="">Sila Pilih</s:option>
                        <c:forEach items="${actionBean.p.senaraiHakmilik}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                </s:select>
            </p>
            <%--<display:table class="tablecloth" name="${actionBean.p.senaraiHakmilik}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                <c:if test="${edit}">
                        <display:column>
                            <input type="radio" id="" name="rb" onclick="doGetContent('${line.hakmilik.idHakmilik}', '${line_rowNum}');"
                                   class="checkbox"/>
                        </display:column>
                        </c:if>
                        <display:column title="Hakmilik" property="hakmilik.idHakmilik"/>
                        <display:column title="Daerah" property="hakmilik.daerah.nama"/>
                        <display:column title="Bandar / Pekan / Mukim" property="hakmilik.bandarPekanMukim.nama"/>
            </display:table>--%>
        </div>
        </fieldset>
        <br/>
        <div id="div_content" class="content" align="center"/>
    </s:form>
</div>