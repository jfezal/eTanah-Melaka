<%-- 
    Document   : carian_hakmilik
    Created on : 15-Oct-2009, 10:22:28
    Author     : md.nurfikri
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {        
        $(".popup").click( function() {            
            
            window.open("hakmilik?hakmilikDetail&idHakmilik="+$("#hakmilik").val(), "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#add").click( function(){
            frm = this.form;
            //TODO :
            var len = $(".daerah1").length;
                    
            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var id = $(".idhakmilik"+i).text();
                    var caw = $(".cawangan"+i).text();
                    var daerah = $(".daerah"+i).text();
                    var lot = $(".lot"+i).text();
                    var luas = $(".luas"+i).text();
                    var noLitho = $(".noLitho"+i).text();
                    self.opener.addRows(id, caw, daerah, lot, luas, noLitho);
                }
            }
            self.close();
        });
    });
</script>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.HakmilikActionBean">
        <fieldset class="aras1">
            <legend>
                Carian Hakmilik
            </legend>
            <p>
                <label for="Id Hakmilik">ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" id="hakmilik"/> 
            </p>
            <p>
                <label>&nbsp;</label> 
                <s:submit name="searchHakmilik" value="Cari" class="btn"/>
            </p>           
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.list}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="hakmilik" id="line">
                    <display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idHakmilik}"/></display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik" class="popup idhakmilik${line_rowNum}" />
                    <display:column property="cawangan.name" title="Cawangan" class="cawangan${line_rowNum}"/>
                    <display:column property="daerah.nama" title="Daerah" class="daerah${line_rowNum}"/>
                    <display:column property="lot.nama" title="Lot" class="lot${line_rowNum}"/>
                    <display:column property="luas" title="Luas" class="luas${line_rowNum}"/>
                    <display:column property="noLitho" title="No Litho" class="noLitho${line_rowNum}"/>
                </display:table>
             </div>            
            <c:if test="${fn:length(actionBean.list) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="add" id="add" value="Tambah" class="btn"/>
                </p>
            </c:if>
        </fieldset>
    </s:form>
</div>
