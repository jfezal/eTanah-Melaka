
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

   function doGetContent(id) {
       if (id == '') {
           $('#div_content').html('');
       } else{
            doBlockUI();
               var url = '${pageContext.request.contextPath}/daftar/kaveat?doGetPartialPage&idHakmilik=' + id;
               $.ajax({
                   url : url,
                   dataType : 'html',
                   success : function(data) {$('#div_content').html(data);doUnBlockUI();}
               });
       }
       
   }
</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.daftar.Kaveat">
        <fieldset class="aras1">
        <legend>Senarai Hakmilik</legend>
        <br/>
        &nbsp;&nbsp;<font color="black" size="2">Sila pilih hakmilik terlibat.</font>
        <div class="content">

            <p>
                <label>ID Hakmilik : </label>
                <s:select name="" onchange="doGetContent(this.value);">
                <s:option value="">Sila Pilih</s:option>
                <c:forEach items="${actionBean.senaraiHakmilik}" var="item">
                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                        ${item.hakmilik.idHakmilik} (${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama})
                    </s:option>
                </c:forEach>
                </s:select>
            </p>

            <%--display:table class="tablecloth" name="${actionBean.senaraiHakmilik}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                        <display:column>
                            <input type="radio" id="" name="rb" onclick="doGetContent('${line.hakmilik.idHakmilik}', '${line_rowNum}');"
                                   class="checkbox"/>
                        </display:column>
                        <display:column title="Hakmilik" property="hakmilik.idHakmilik"/>
                        <display:column title="Daerah" property="hakmilik.daerah.nama"/>
                        <display:column title="Bandar / Pekan / Mukim" property="hakmilik.bandarPekanMukim.nama"/>
            </display:table--%>
        </div>
        </fieldset>
        <br/>
        <div id="div_content" class="content"/>
    </s:form>
</div>