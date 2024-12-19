<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function doViewReport(v) {
    <%--var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
    window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");--%>
        var idmohon = '${actionBean.mohonHakmilik.permohonan.idPermohonan}';
        var report = 'STRJadualPetak_MLK.rdf';
        var url = "reportName=" + report + "%26report_p_id_mohon=" + idmohon;
        //alert(url);
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function save(event, f) {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }, 'html');
    }

    $(document).ready(function () {
        var luasPetak = 0.00;
        var luasPetakAksr = 0.00;
        var kadarPetak = 0.00;
        var totalCukai = 0.00;
        var len = $('.listHm').length;
        var hm = '${actionBean.mohon.senaraiHakmilik[0].hakmilik.idHakmilik}';
        for (var i = 0; i < len; i++) {
            luasPetak = $('#luas' + i).val();
            luasPetakAksr = $('#luasAksr' + i).val();
            kadarPetak = $('#cukai' + i).val();
            if (luasPetakAksr === undefined) {
                luasPetakAksr = 0.00;
            }

            var cukai = Math.ceil((parseFloat(luasPetak) + parseFloat(luasPetakAksr)) * kadarPetak);
            var kosRendah = '${actionBean.skimStrata.kosRendah}';
            if (kosRendah === 'Y') {
                if (cukai < 10) {
                    cukai = 10;
                }
            }
            if (kosRendah === 'T') {
                if (cukai < 15) {
                    cukai = 15;
                }
            }
            if (hm.includes("GMM") || hm.includes("HMM")) {
                cukai = parseFloat(cukai) / 2;
            }

            $('#totalCukai' + i).val(cukai);
            totalCukai = totalCukai + cukai;
        }

        $('#jum').val(totalCukai);
    });
</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>
<s:form beanclass="etanah.view.strata.JadualPetakExcel">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <table>

            Jumlah Petak : ${fn:length(actionBean.listHM)}<br />
            Jumlah Petak Aksesori : ${fn:length(actionBean.listPetakAksr)}<br /><br />

            Jumlah Cukai : <s:text name="jum" id="jum" readonly="readonly" /> <br /><br />
            <!--Kadar Cukai Bangunan Bertingkat : ${actionBean.cukaiPetak.kadar}<br />-->
            <!--Kadar Cukai Landed: ${actionBean.cukaiPetak.kadarLanded}<br /><br />-->

            <fieldset>
                <br />
                <p><label>Jenis Bangunan :</label>
                    <s:select name="kategoriBangunan" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="DC027">Perniagaan</s:option>
                        <s:option value="DC029">Kediaman</s:option>
                        <s:option value="DC051">Kediaman(Landed Strata)</s:option>
                    </s:select>
                <p>
                <p><label>Pengkelasan Tanah :</label>
                    <s:select name="kelasTanah" >
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKelasTanah}" value="kod" label="nama"/>
                    </s:select>
                <p>
                <p><label>Kos Rendah :</label>
                    <s:select  name="skimStrata.kosRendah" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="Y">YA</s:option>
                        <s:option value="T">TIDAK</s:option>
                    </s:select>
                <p>

                    <c:if test="${actionBean.skimStrata == null}" >
                    <center><s:button class="btn" name="simpanSkim" value="Simpan" onclick="save(this.name, this.form);"/></center>
                        <%--<s:button name="SimpanMaklumatBayaranNS" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>--%>
                    </c:if>
                    <c:if test="${jana}">
                    <br />
                    <center>
                        <s:button class="btn" name="janaCukai" value="Jana Cukai" onclick="save(this.name, this.form);"/> 
                        <s:button class="btn" name="janaCukai" value="Simpan Cukai" onclick="save(this.name, this.form);"/>
                    </center>
                    </c:if>

                <br />
            </fieldset>
            <br />
            <lagend><font color="blue" ><h4>Pengiraan Cukai Petak </h4></font></lagend>
            <p>
                <c:set value="0" var="count1"/>
                <c:set value="0" var="count"/>
                <display:table style="width:80%" class="tablecloth" name="${actionBean.hm}" id="line">
                    <display:column title="Id Hakmilik Strata" class="listHm">
                        ${line[0]}
                        <s:hidden name="ptk${count}" id="ptk${count}" value="${line[0]}" />
                    </display:column>
                    <c:set value="0" var="total"/>
                    <display:column title="a - Luas Petak (m²)">
                        <c:set value="${total + line[1]}" var="total"/>
                        ${line[1]}
                        <s:hidden name="luas" id="luas${count}" value="${line[1]}" />
                    </display:column>
                    <display:column title="b - Luas Petak Aksesori (m²)">
                        ${line[2]}
                        <s:hidden name="luasAksr" id="luasAksr${count}" value="${line[2]}" />
                    </display:column>
                    <display:column title="Kadar">
                        <c:choose>
                            <c:when test="${fn:contains(line[0],'L') || fn:contains(line[0],'PL')}" >
                                ${actionBean.cukaiPetak.kadarLanded}
                                <s:hidden name="cukai" id="cukai${count}" value="${actionBean.cukaiPetak.kadarLanded}" />
                            </c:when>
                            <c:otherwise>
                                ${actionBean.cukaiPetak.kadar}
                                <s:hidden name="cukai" id="cukai${count}" value="${actionBean.cukaiPetak.kadar}" />
                            </c:otherwise>
                        </c:choose>
                    </display:column>
                    <display:column title="Cukai Petak - kadar*(a+b)" >
                        <s:text name="totalCukai${count}" id="totalCukai${count}" class="total" readonly="readonly"/>
                    </display:column>

                    <c:set value="${count +1}" var="count"/>
                </display:table>
            </p>
        </table>
    </fieldset>
</s:form>

