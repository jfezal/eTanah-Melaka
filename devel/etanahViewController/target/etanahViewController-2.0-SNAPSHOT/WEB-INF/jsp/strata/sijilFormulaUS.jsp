
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script language="javascript" type="text/javascript">
    function doCetak(f) {


    }
</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.MaklumatSijilFormulaUS">
    <s:errors/>
    <s:messages/>
    <fieldset class="aras1">
        <legend>Maklumat Sijil Formula Unit Syer</legend>
        <p> 
            <label>Formula :</label>
            <s:textarea name="projek.formula" cols="30"  rows="3" class="normal_text"/>
        </p>
        <p>
            <label>Kegunaan Bangunan :</label>
            <s:select name="gunaBngn">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
            </s:select>
        </p>
        <p>
            <label>Jenis Kengunaan Bangunan (Lain - Lain):</label> 
            <s:text name="projek.kegunaanLain" size="60" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label>Nama Skim :</label>
            <s:text name="projek.jenisProjek" onkeyup="this.value=this.value.toUpperCase();" size="50"/>

        </p>
        <p>
            <label>Bilangan Petak :</label>
            <s:text name="projek.jumlahSemuaUnit" onkeyup="this.value=this.value.toUpperCase();" size="10"/>
        </p>
        <p>
            <label>Bilangan Blok Sementara (jika ada) :</label>
            <s:text name="projek.jumlahUnitSementara" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
        </p>
        <p>
            <label>Pemohon :</label>
            <s:text name="projek.namaPemaju" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
        </p>
        <p>
            <label>Pemilik :</label>
            <s:text name="projek.pemilik" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
        </p>
        <p>
            <label>No. Rujukan Fail :</label>
            <s:text name="projek.noRujFail" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
        </p>
        <p>
            <label>No. Rujukan Pelan Bangunan :</label>
            <s:text name="projek.noRujukanProjek" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
            <br/>
        </p>
        <p>
            <label>Arkitek / Jurutera :</label>
            <s:text name="projek.arkitek" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
            <br/>
        </p>
        <p>
            <label>COB :</label>
            <s:select name="projek.unitDiJual" value = "${actionBean.projek.unitDiJual}">
                <s:option value="">Pilih ...</s:option>
                <s:option value="1">MBMB</s:option>
                <s:option value="2">MPHTJ</s:option>
                <s:option value="3">MPJ</s:option>
                <s:option value="4">MPAG</s:option>
            </s:select>
            <br/>
        </p>
        <c:if test="${actionBean.stageId eq 'kemasukan'}">
            <p>
                <label>&nbsp;</label>&nbsp;
                <s:button class="longbtn" value="Simpan" name="save" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PFUS' || actionBean.permohonan.kodUrusan.kod eq 'SFUS'
                          || actionBean.permohonan.kodUrusan.kod eq 'PSBS'}">
                  <p>
                     Sekiranya terdapat masalah pada tab 'Jadual Petak', sila hapus jadual petak dan kemaskini maklumat 
                          jadual petak di dalam excel 
                      <s:button class="longbtn" name="delete" value="Hapus Jadual Petak"  onclick="doSubmit(this.form,this.name,'page_div')" />
                  </p>
            </c:if>
        </c:if>

        <c:if test="${actionBean.stageId eq 'maklum_tolak'}">
            <p>
                <label>&nbsp;</label>&nbsp;
                <s:button class="longbtn" value="Simpan" name="save" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </c:if>

    </fieldset>
</s:form>
