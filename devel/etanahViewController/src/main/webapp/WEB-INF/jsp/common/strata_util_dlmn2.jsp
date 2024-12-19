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
<script type="text/javascript">

    $(document).ready(function() {
        $('#petakAksr').hide();
        $('#tmbhPelan').hide();
    });
    function refreshpage(frm){
        self.close();
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();


        var url = '${pageContext.request.contextPath}/strataUtilDalaman?refresh&daerah='+ daerah+'&bandarPekanMukim=' + bandarPekanMukim + '&kodHakmilik=' + kodHakmilik + '&noHakmilik=' + noHakmilik;
        frm.action = url;
        frm.submit();
    }
    function petakAksr() {
        var pilih = document.getElementById("pickjenis1").value;
        $('#petakAksr').show();
        $('#tmbhPelan').hide();
    }
    function tmbhPelan() {
        var pilih = document.getElementById("pickjenis2").value;
        $('#tmbhPelan').show();
        $('#petakAksr').hide();
        $('#pSangkutAks').val('');
        $('#namaAks').val('');
        $('#LokasiAks').val('');
        $('#kodKegunaanPetakAks').val('0');
    }
    function popup(f)
    {
        var negeri = $('#negeri').val();
        var daerah = $('#daerah').val();
        var bandarPekanMukim = $('#bandarPekanMukim').val();
        var kodHakmilik = $('#kodHakmilik').val();
        var noHakmilik = $('#noHakmilik').val();
        var idHakmilikEdit = f;

        window.open("${pageContext.request.contextPath}/strataUtilDalaman?petakAksesoriPopup&idHakmilikEdit="+idHakmilikEdit+ '&bandarPekanMukim='+bandarPekanMukim+ '&noHakmilik='+noHakmilik+ '&kodHakmilik='+kodHakmilik+ '&daerah='+daerah, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }

</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.stripes.common.strataUtilDalaman">
    <s:messages />
    <s:errors />
    <div id="aras1">
        <c:if test="${!editPetak}">
            <fieldset class="aras1">
                <font size="2"><b><legend>Petak Aksesori</legend></b></font>
                <s:hidden name="idHakmilikEdit" id="idHakmilikEdit"/>
                <s:hidden name="daerah" id="daerah" value="${actionBean.hakmilikEdit.daerah.kod}"/>
                <s:hidden name="bandarPekanMukim" id="bandarPekanMukim" value="${actionBean.hakmilikEdit.bandarPekanMukim.kod}"/>
                <s:hidden name="kodHakmilik" id="kodHakmilik" value="${actionBean.hakmilikEdit.kodHakmilik.kod}"/>
                <s:hidden name="noHakmilik" id="noHakmilik" value="${actionBean.hakmilikEdit.noHakmilik}"/>
                <center>
                <p>
                    <font><h4>${actionBean.hakmilikEdit.idHakmilik}</h4></font>
                </p>
                <br><br>
                
                    <input type="radio" name="pickjenis" id="pickjenis1" value="petakAksr" onclick="petakAksr()"/>Tambah Petak Aksesori
                    <input type="radio" name="pickjenis" id="pickjenis2" value="tmbhPelan" onclick="tmbhPelan()"/>Tambah No.Pelan
                </center>
                <br>
                <div id="petakAksr">
                    <p>
                        <label>Petak Bersangkutan : </label> <s:text name="pSangkutAks" id="pSangkutAks" value="${actionBean.hakmilikEdit.noBangunan} - ${actionBean.hakmilikEdit.noTingkat} - ${actionBean.hakmilikEdit.noPetak}"/>
                    </p>
                    <p><label>Petak Aksesori : </label>
                        <c:if test="${empty actionBean.senaraiPetakAksesori}">Tiada Data</c:if>
                        <c:if test="${!empty actionBean.senaraiPetakAksesori}">
                            <c:forEach items="${actionBean.senaraiPetakAksesori}" var="line">
                                <c:if test="${line.nama ne null}">
                                A${line.nama}
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </p>
                    <p>
                        <label>Masukkan Petak Aksesori (A):</label>
                        <s:text name="namaAks" id="namaAks" size="5"/>
                    </p>
<!--                    <p>
                        <label>Masukkan No.Pelan Petak Aksesori (PA(B)):</label>
                        <s:text name="noPelanAks" id="noPelanAks" size="20"/>
                    </p>-->
                    <p>
                        <label>Lokasi :</label>
                        <s:text name="LokasiAks" id="LokasiAks" size="40"/>
                    </p>
                    <p>
                        <label>Kegunaan Petak Aksesori :</label>
                        <s:select name="kodKegunaanPetakAks">
                            <%--<s:select name="kodKegunaanPetakAks" value="${actionBean.mhnbgn.kodKegunaanBangunan.kod}">--%>
                            <s:option value="0">Sila Pilih</s:option>
                            <%--<s:options-collection collection="${listUtil.senaraiKodKegunaanPetakAks}" label="nama" value="kod"/>--%>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanPetakAksesori}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label>Luas Petak Aksesori :</label>
                        <s:text name="luasAks" id="luasAks" size="8"/>
                    </p>
                    <br>
                    <br>
                </div>
                <div id="tmbhPelan">
                    <p>
                        <label>Masukkan No.Pelan Tambahan (PA(B)):</label>
                        <s:text name="noPelanAks1" id="noPelanAks1" size="20"/>
                    </p>
                    <br>
                    <br>
                </div>
                <center>
                    <s:submit name="simpanPetakAk" value="Simpan" class="btn" onclick="simpanPetakAk(this.name, this.form)"/>&nbsp;
                    <s:button class="longbtn" name="tutup" value="Tutup" id="tutup" onclick="refreshpage(this.form);"/>
                </center>
            </fieldset>
        </c:if>
        <br>

        <p>&nbsp;</p>

        <c:if test="${editPetak}">
            <fieldset class="aras1">
                <s:hidden name="idHakmilikEdit" id="idHakmilikEdit"/>
                <p>
                    <label>Id Hakmilik :</label>
                    ${actionBean.hakmilikEdit.idHakmilik}
                </p>
                <p>
                    <label>No Bangunan :</label>
                    ${actionBean.hakmilikEdit.noBangunan}
                </p>
                <p>
                    <label>No Tingkat :</label>
                    <s:text name="noTingkatEdit" id="noTingkatEdit" value="${actionBean.hakmilikEdit.noTingkat}" size="15" onkeydown="return down(this, event);"/>
                </p>
                <p>
                    <label>No Petak :</label>
                    ${actionBean.hakmilikEdit.noPetak}
                </p>
                <p>
                    <label>No Pelan PA(B):</label>
                    <s:text name="noPelanEdit" id="noPelanEdit" size="50" value="${actionBean.hakmilikEdit.noPelan}" onkeydown="return down(this, event);"/>
                </p>
                <p>
                    <label>Unit Syer :</label>
                    <s:text name="unitSyerEdit" id="unitSyerEdit" value="${actionBean.hakmilikEdit.unitSyer}" onkeydown="return down(this, event);"/>
                </p>
                <p>
                    <label>Luas :</label>
                    <s:text name="luasEdit" id="luasEdit" value="${actionBean.hakmilikEdit.luas}" size="30" onkeydown="return down(this, event);"/>
                </p>
                <p>
                    <label>Petak Aksesori :</label>
                    <s:button class="longbtn" name="petakAksesoriEdit" value="Tambah" id="petakAksesoriEdit" onclick="popup('${actionBean.hakmilikEdit.idHakmilik}');"/>
                </p>
                <center>
                    <s:submit name="simpanPetakEdit" value="Simpan" class="btn" onclick="simpanPetakEdit(this.name, this.form)"/>&nbsp;
                    <s:button class="longbtn" name="tutup" value="Tutup" id="tutup" onclick="refreshpage(this.form);"/>
                </center>
            </fieldset>
        </c:if>

    </div>
</s:form>
