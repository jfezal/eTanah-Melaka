<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">

    $(document).ready(function() {
        document.getElementById("simpan").disabled = true;
        document.getElementById("simpan2").disabled = true;
    });

    function reset1(f) {
        $("#idHakmilik").val('');
        $("#idPermohonan").val('');

    }

    function doProcess() {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var param = '';

        $('.number').each(function() {
            var ckd = $('#chkbox' + $(this).val()).is(":checked");
            if (ckd) {
                param = param + $('#chkbox' + $(this).val()).val() + "&luas="
                        + $('#luasPetak' + $(this).val()).val();
            }
        });

        if (param === '') {
            alert('Sila tanda luas untuk disimpan.');
            return;
        }

        var frm = document.form1;
        frm.action = '${pageContext.request.contextPath}/strata/KemasukanLuas?simpan' + param;
        frm.submit();

    }

    function doProcess2() {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var param = '';

        $('.number2').each(function() {
            var ckd = $('#chkbox2' + $(this).val()).is(":checked");
            if (ckd) {
                param = param + $('#chkbox2' + $(this).val()).val() + "&luas="
                        + $('#luasAksr' + $(this).val()).val();
            }
        });

        if (param === '') {
            alert('Sila tanda luas untuk disimpan.');
            return;
        }

        var frm = document.form1;
        frm.action = '${pageContext.request.contextPath}/strata/KemasukanLuas?simpan' + param;
        frm.submit();

    }

    function selectAll(a) {

        $('.number').each(function() {
            var c = document.getElementById("chkbox" + $(this).val());
            c.checked = a.checked;
        });

    }

    function selectAll2(a) {
        $('.number2').each(function() {
            var c = document.getElementById("chkbox2" + $(this).val());
            c.checked = a.checked;
        });
    }

    function enableButton() {
        document.getElementById("simpan").disabled = false;
    }
    function enableButton2() {
        document.getElementById("simpan2").disabled = false;
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.strata.utiliti.KemasukanLuas" name="form1">

    <s:messages />
    <s:errors />

    <fieldset class="aras1">
        <legend>Kemasukan Luas</legend>
        <br />
        <font color="red">** Sila masukkan Id Hakmilik Induk untuk mengemaskini luas keseluruhan petak </font>
        <br />
        <font color="red">** Sila masukkan Id Hakmilik Strata untuk mengemaskini luas petak yang terlibat </font>
        <br />
        <br />
        <br />

        <p>
            <label>Id Hakmilik :</label>
            <s:text name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();" size="35%" id="idHakmilik"/>

        </p>
        <!--        <p>
                    <label>Kategori Luas :</label>
        <s:select name="luasKatg" id="luasKatg" >
            <s:option value="">Sila Pilih</s:option>
            <s:option value="petak">Luas Petak</s:option>
            <s:option value="aksr">Luas Petak Aksesori</s:option>
        </s:select>

    </p>-->
        <br />
        <center><s:submit name="cari" value="Cari" class="btn" />&nbsp;
            <s:submit name="rehydrate" value="Isi Semula" class="btn" /></center>
        <br />
        <br />

        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <c:if test="${fn:length(actionBean.senaraiLuasPetak) > 0}">
                    <legend>Senarai Luas Petak</legend>
                    <c:set var="ind" value="1"/>
                    <display:table name="${actionBean.senaraiLuasPetak}"  class="tablecloth"  requestURI="/strata/KemasukanLuas"
                                   pagesize="15" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true" >
                            <input type="hidden" value="${ind}" id="len" class="number"/>${line_rowNum}
                        </display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik" />
                        <display:column title="No Bangunan" property="noBangunan" />
                        <display:column title="No Tingkat" property="noTingkat" />
                        <display:column title="No Petak" property="noPetak" />
                        <display:column title="<font color='red'><em>*</em>  </font>Luas <input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                            <s:text name="luasPetak" id="luasPetak${line_rowNum}" value = "${line.luas}" onchange="enableButton();"/>
                            <s:checkbox class="klikSemua" name="checkbox" id="chkbox${line_rowNum}"
                                        value="&hakmilik=${line.idHakmilik}&nama="/>
                        </display:column>
                        <c:set var="ind" value="${ind + 1}"/>
                    </display:table>
                    <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="doProcess();"/>
                </c:if>
                <br>
            </fieldset>
        </div>
        <br />
        <br />
        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <c:if test="${fn:length(actionBean.senaraiLuasPetakAksr) > 0}">
                    <legend>Senarai Luas Petak Aksesori</legend>
                    <c:set var="ind2" value="1"/>
                    <display:table name="${actionBean.senaraiLuasPetakAksr}"  class="tablecloth"  requestURI="/strata/KemasukanLuas"
                                   pagesize="15" cellpadding="0" cellspacing="0" id="line2">
                        <display:column title="No" sortable="true">
                            <input type="hidden" value="${ind2}" id="len2" class="number2"/>${line2_rowNum}
                        </display:column>
                        <display:column title="Id Hakmilik" property="hakmilik.idHakmilik" />
                        <display:column title="Nama Petak Aksesori">
                            A${line2.nama}
                        </display:column>
                        <display:column title="<font color='red'><em>*</em>  </font>Luas <input type='checkbox' id='semua2' name='semua2' onclick='javascript:selectAll2(this);' />">
                            <s:text name="luasAksr" id="luasAksr${line2_rowNum}" value = "${line2.luas}" onchange="enableButton2();"/>
                            <s:checkbox class="klikSemua2" name="checkbox2" id="chkbox2${line2_rowNum}" 
                                        value="&hakmilik=${line2.hakmilik.idHakmilik}&nama=${line2.nama}"/>
                        </display:column>
                        <c:set var="ind2" value="${ind2 + 1}"/>
                    </display:table>
                    <s:button name="simpan2" id="simpan2" value="Simpan" class="btn" onclick="doProcess2();"/>
                </c:if>
                <br>
            </fieldset>
        </div>



    </fieldset>
    <br>
    <br>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <c:if test="${fn:length(actionBean.senaraiLuasPetak) > 0}">
                <legend>Hasil Jumlah Luas Petak dan Jumla Petak Aksesori</legend>
                <center>
                    <display:table name="actionBean.total"  class="tablecloth"  
                                   pagesize="15" cellpadding="0" cellspacing="0" id="line3">
                        <display:column title="Luas Petak Keseluruhan">
                            ${actionBean.luasTotal}
                        </display:column>
                        <display:column title="Luas Petak Aksesori Keseluruhan">
                            ${actionBean.luasTotalAksr}
                        </display:column>
                        <display:column title="Luas Keseluruhan">
                            ${actionBean.total}
                        </display:column>
                    </display:table>
                    <br>
                </center>
            </c:if>
        </fieldset>
    </div>

</s:form>
