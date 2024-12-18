<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function refreshpage(frm){
        self.close();
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();

        var url = '${pageContext.request.contextPath}/strataConversion?refresh&daerah='+ daerah+'&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik;
        frm.action = url;
        frm.submit();
    }

    function hapus1(x,b,f,frm)
    {
        var idHakmilikPetakAks = x;
        var idAksesori = b;
        var idHakmilikInduk = $('#idHakmilikInduk').val();

        var url = '${pageContext.request.contextPath}/strataConversion?hapusAks&idHakmilikPetakAks='+ idHakmilikPetakAks+'&idAksesori=' + idAksesori+'&idHakmilikInduk=' + idHakmilikInduk;
        frm.action = url;
        frm.submit();

    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.stripes.common.strataConversion">
    <s:messages />
    <s:errors />
    <br>
    <div>
        <fieldset class="aras1">
            <font size="2"><b><legend>Petak Aksesori</legend></b></font>
            <br>

            <s:hidden name="idHakmilikInduk" id="idHakmilikInduk"/>
            <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiPetakAksesori2}" requestURI="/strataConversion" pagesize="100" cellpadding="1" cellspacing="1" id="line">
                <c:if test="${line.nama ne null}">
                    <display:column title="Id Hakmilik Strata">${line.hakmilik.idHakmilik}</display:column>
                    <%--<display:column title="No Bangunan">${line.noBangunan}</display:column>--%>
                    <%--<display:column title="No Tingkat">${line.noTingkat}</display:column>--%>
                    <display:column title="Nama">A${line.nama}</display:column>
                    <display:column title="Kegunaan Petak">${line.kegunaaanPetak.nama}</display:column>
                    <display:column title="Lokasi">${line.lokasi}</display:column>
                    <display:column title="No.Pelan PA(B)">${line.noPelan}</display:column>
                    <display:column title="Luas Aksesori">${line.luas}</display:column>
                    <%--<display:column title="Petak Bersangkutan">${line.lokasi}</display:column>--%>
                    <%-- <display:column title="Petak Aksesori">
                         <c:forEach items="${actionBean.senaraiPetakAksesori2}" var="line2">
                             <c:if test="${line.idHakmilik eq line2.hakmilik.idHakmilik}">
                                 ${line2.nama}
                             </c:if>
                         </c:forEach>
                     </display:column>--%>
                    <display:column title="Tindakan" style="width:5%">
                        <s:button class="longbtn" name="hapus" value="Hapus" id="hapus" onclick="hapus1('${line.hakmilik.idHakmilik}','${line.idAksesori}',this.value,this.form);"/>
                    </display:column>
                </c:if>
            </display:table>
                    <br><br>
            No.Pelan Tambahan Bagi Petak Strata
            <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiPelanTambahan}" pagesize="100" cellpadding="1" cellspacing="1" id="line2">
                    <display:column title="Id Hakmilik Strata">${line2.hakmilik.idHakmilik}</display:column>
                    <display:column title="No.Pelan PA(B)">${line2.noPelan}</display:column>
                    <display:column title="Tindakan" style="width:5%">
                        <s:button class="longbtn" name="hapus" value="Hapus" id="hapus" onclick="hapus1('${line2.hakmilik.idHakmilik}','${line2.idAksesori}',this.value,this.form);"/>
                    </display:column>
            </display:table>
        </fieldset>
        <br>
        <center>
            <%--<s:submit name="simpanPetakAk" value="Simpan" class="btn" onclick="simpanPetakAk(this.name, this.form)"/>&nbsp;--%>
            <s:button class="longbtn" name="tutup" value="Tutup" id="tutup" onclick="refreshpage(this.form);"/>
        </center>
        <p>&nbsp;</p>
    </div>
</s:form>
