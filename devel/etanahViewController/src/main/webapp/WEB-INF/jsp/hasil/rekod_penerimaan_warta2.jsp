<%-- 
    Document   : rekod_penerimaan_warta2
    Created on : Dec 26, 2009, 12:34:00 AM
    Author     : nurfaizati
--%>



<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    function show(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    <%--function cetakDraf(){
        window.open("${pageContext.request.contextPath}/hasil/penerimaan_warta?cetakDraf", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }--%>

    function cetakDraf(f,idMohon){
       $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var negeri = "${actionBean.negeri}";
        var form = $(f).formSerialize();
        var report = "";
        if(negeri == "melaka")
            report = "Notis8ASS130ENGBM_002.rdf";
        if(negeri != "melaka")
            report = "Notis8ASS130ENGBM_002.rdf";
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_mohon="+idMohon, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        $.unblockUI();
    }
</script>

<s:form beanclass="etanah.view.stripes.hasil.RekodWartaActionBean">
    <s:messages />
    <s:errors />&nbsp;
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Yang Dicadangkan Untuk Rekod Warta
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                               pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/penerimaan_warta" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <display:column title="No. Akaun" property="hakmilik.akaunCukai.noAkaun" class="${line_rowNum}" />
                    </c:if>
                    <display:column title="ID Hakmilik"><a href="#" onclick="show('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                    <display:column title="No. Rujukan">${actionBean.senaraiNoRujukan[line_rowNum - 1]}</display:column>
                    <display:column title="Jenis dan Nombor Hakmilik" class="${line_rowNum}">
                        ${line.hakmilik.kodHakmilik.kod}&nbsp;&nbsp;
                        ${line.hakmilik.noHakmilik}<br>
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="${line_rowNum}"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No. Lot" class="${line_rowNum}"/>
                    <display:column title="Luas"><div align="right"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>${line.hakmilik.kodUnitLuas.kod}</div></display:column>
                    <display:column title="Jumlah Tunggakan(RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki}"/></div></display:column>
                </display:table>
            </div>
            <%--<p align="right">
                <s:button class="btn" onclick="cetakDraf(this.form,'${actionBean.permohonan.idPermohonan}');" name="" value="Cetak Draf"/>
            </p>--%>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Rekod Warta
            </legend>
            <p>
                <label> No Warta :</label>
                <s:text name="noWarta" id="" size="21"/>
            </p>
            <p>
                <label> Tarikh Penghantaran :</label>
                <s:text name="dateHantarWarta" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label> Tarikh Penerimaan :</label>
                <s:text name="dateTerimaWarta" id="datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <br>
            <p align="right">
                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn" />
            </p>
        </fieldset>
    </div>
</s:form>
