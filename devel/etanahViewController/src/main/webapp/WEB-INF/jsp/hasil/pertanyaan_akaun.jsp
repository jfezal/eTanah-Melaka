<%--
	Document: pertanyaan_akaun
	Author: Mohd Hairudin Mansur
	Version: 1.0 2 December 2009
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" language="javascript" >
    function validate(){
        var kodAkaun = document.getElementById('kodAkaun');
        var jenisPengenalan = document.getElementById('jenisPengenalan');
        var noPengenalan = document.getElementById('noPengenalan');
        if(kodAkaun.value == "0"){
            alert("Sila pilih Jenis Akaun Amanah");
            return false;
        }
        <%--else if(jenisPengenalan.value == "0"){
            alert("Sila pilih Jenis Pengenalan");
            return false;
        }else if(noPengenalan.value == ""){
            alert("Sila masukkan Nombor Pengenalan");
            return false;
        }--%>
    }

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisPengenalan').val();
        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noPengenalan').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noPengenalan').val(tst);
            }
        }
    }

    function clearNoPengenalan(){
        $('#noPengenalan').val('');
    }
</script>


<s:form beanclass="etanah.view.stripes.hasil.PertanyaanAkaunActionBean" id="pertanyaan_akaun">
<s:errors/>
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pertanyaan, Pengwujudan Dan Penyelenggaraan Akaun Amanah</font>
            </div>
        </td>
    </tr>
</table></div>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Carian Akaun</legend>
		<div class="instr-fieldset">
                    <font color="red">Perhatian :</font> Medan bertanda<em>*</em>adalah mandatori.
                </div>
        <div class="content">
            <p>
                <label><em>*</em> Jenis Akaun Amanah/Deposit/Lejar Individu :</label>
                <s:select name="kodAkaun" id="kodAkaun">
                    <option value="0">Pilih....</option>
                    <c:forEach items="${listUtil.senaraiKodAkaunJenisAmanah}" var="j" >
                        <option value="${j.kod}" >${j.kod} - ${j.nama}</option>
                    </c:forEach>
                </s:select>
            </p>
            <br>
            <p>
                <label>No. Akaun :</label>
                <s:text name="noAkaun" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="idHakmilik" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label >No Lot :</label>
                <s:text name="noLot" id="noLot"  maxlength="15" size="30"/>
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select id="bpm" name="kodBPM" style="width:200px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>ID Permohonan :</label>
                <s:text name="idPermohonan" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>No. Fail/Rujukan :</label>
                <s:text name="noFail" size="30" id="fail"/>
            </p>
            <p>
                <label>Nama Pemegang :</label>
                <s:text name="pemegang" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="jenisPengenalan" id="jenisPengenalan" onchange="clearNoPengenalan();" style="width:200px;">
                    <s:option value="0">Pilih...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama"/>
                </s:select>
            </p>
            <p>
                <label>No Pengenalan :</label>
                <s:text name="noPengenalan" size="30" id="noPengenalan" onblur="this.value=this.value.toUpperCase();" onkeyup="dodacheck(this.value);"/>
            </p>
        </div>
    </fieldset>
</div>
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="50%" height="20" align="right">
                <s:submit name="searchAkaun" value="Cari" class="btn" onclick="return validate()"/>
                <s:button  name="" value="Isi Semula" class="btn" onclick="clearText('pertanyaan_akaun');"/>
                <s:submit name="addNewAkaun" value="Tambah Akaun" class="btn"/>
            </td>
        </tr>
    </table></div>

<c:if test="${actionBean.showDisplayTable}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Akaun</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listAkaun}" pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/pertanyaan_akaun" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pemegang.nama" title="Nama" />
                    <display:column title="No Akaun">
                        <s:link beanclass="etanah.view.stripes.hasil.PertanyaanAkaunActionBean" event="akaunDetail">
                            <s:param name="akaunID" value="${line.noAkaun}" /> ${line.noAkaun}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="kodAkaun.nama" title="Jenis Akaun" />
                    <display:column property="pemegang.jenisPengenalan.nama" title="Jenis Pengenalan" />
                    <display:column property="pemegang.noPengenalan" title="No Pengenalan" />
                </display:table>
            </div>
        </fieldset>
    </div>
</c:if>
</s:form>
