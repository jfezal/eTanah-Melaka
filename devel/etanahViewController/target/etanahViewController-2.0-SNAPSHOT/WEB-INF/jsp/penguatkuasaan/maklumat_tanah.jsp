<%-- 
    Document   : maklumat_tanah
    Created on : Jun 30, 2010, 12:22:46 PM
    Author     : nurshahida.radzi
    modify by sitifariza.hanim (12042011)
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function simpan(){
        idHakmilik = $('#idHakmilik').val();
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_tanah?simpan&idHakmilik='+idHakmilik;
    <%--alert(url);--%>
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatTanahActionBean" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:errors/>
    <s:messages/>

    <c:if test="${edit eq true}" >
        <div class="content">
            <p>
                <label for="ID Hakmilik">ID Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" size="25" value="${actionBean.idHakmilik}"/>
                <s:button class="btn" value="Cari" name="new" id="new" onclick="simpan();"/>
            </p>
        </div>
    </c:if>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                        <c:if test="${line.hakmilik.idHakmilik eq null}">
                            Tiada rekod
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line_rowNum}
                        </c:if>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.maklumatAtasTanah" title="Jenis Pengggunaan Tanah" />
                </display:table>
            </div>
        </fieldset>
    </div>
    <br>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Berkepentingan
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.pihakKepentinganList1}" id="line1" class="tablecloth" requestURI="/penguatkuasaan/maklumat_pemohon" pagesize="10">
                    <display:column title="Bil">
                        ${line1_rowNum}
                        <s:hidden name="" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                    <display:column title="Alamat" class="alamat">
                        ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                        ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                        ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                        ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                        ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                        ${line1.pihak.negeri.nama}
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No. Telefon" />
                    <display:column property="pihak.email" title="Alamat Email" />
                    <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                </display:table>

            </div>
        </fieldset>
    </div>
    <br>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.hakmilikWarisList}" id="line2" class="tablecloth" pagesize="10">
                    <display:column title="Bil">
                        ${line2_rowNum}
                        <s:hidden name="x" class="x${line2_rowNum -1}" value=""/>
                    </display:column>
                    <display:column property="nama" title="Nama" />
                    <display:column property="noPengenalan" title="No. Pengenalan" />
                    <display:column title="Syer"> ${line2.syerPembilang }/${line2.syerPenyebut} </display:column>
                    <%--<display:column property="status" title="Status" class="${line2_rowNum}"/>--%>
                </display:table>
            </div>
        </fieldset>
    </div>

</s:form>