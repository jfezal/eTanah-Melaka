<%-- 
    Document   : paparan_maklumat_hakmilik_permohonan_terperinci
    Created on : 12-Jan-2010, 18:44:19
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--<script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>
<%--<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>--%>
<script type="text/javascript">
    function popupHakmilik(){
        var url ="${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?serahHakmilikPopup";
        window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
    }

    $(document).ready( function(){

        var idHakmilik ='${idHakmilik}';

        if(idHakmilik != 'null'){
            url = "${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik="+idHakmilik;
            $.get(url,
            function(data){
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
            });
        }

        <%--var len = ${fn:length(actionBean.hakmilikPermohonanList)};
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik="+$(this).text(),
                function(data){
                    $("#perincianHakmilik").show();
                    $("#perincianHakmilik").html(data);
                });
            });
        }--%>
    });

    function p(v){
        $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik="+v,
        function(data){
            $("#perincianHakmilik").show();
            $("#perincianHakmilik").html(data);
        });
    }
</script>
<div id="maklumatHakmilik">

    <form:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">
        <s:messages/>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik Permohonan
                </legend>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="popup" id="popup" value="Tambah" class="btn" onclick="popupHakmilik();"/>
                </p>
                <p align="center"><label></label>
                    <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="hakmilik${line_rowNum}" style="text-decoration:underline;cursor:hand;" href="/etanah/common/maklumat_hakmilik_permohonan?showForm2&tab=true" paramId="idHakmilik" paramProperty="hakmilik.idHakmilik"  />--%>
                        <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="hakmilik${line_rowNum}" style="text-decoration:underline;cursor:hand;"/>--%>
                        <display:column title="ID Hakmilik"><a href="#" onclick="p('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column property="hakmilik.luas" title="Luas" />
                        <display:column property="hakmilik.cawangan.name" title="Daerah" />
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                    &nbsp;
                </p>

            </fieldset>
        </div>
    </form:form>
</div>
<div id="perincianHakmilik">
</div>




