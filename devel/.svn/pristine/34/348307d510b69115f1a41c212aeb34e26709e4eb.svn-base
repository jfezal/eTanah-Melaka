<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<title>Belakang Kaunter | Rumusan</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
    $('form').submit(function() {
      $.blockUI({
        message: $('#displayBox'),
        css: {
          top: ($(window).height() - 50) / 2 + 'px',
          left: ($(window).width() - 50) / 2 + 'px',
          width: '50px'
        }
      });
    });
  });
</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<p class=title>Langkah 5: Ringkasan</p>

<s:messages />
<s:errors />
<br/>

<p class=instr>Pastikan maklumat di bawah adalah betul.</p>

<s:form action="/daftar/kaunter">

  <fieldset class="aras1">
    <legend>Maklumat Penyerah</legend>

    <p>
      <label>Nama Penyerah :</label>
      ${actionBean.penyerahNama}
    </p>
    <p>
      <label>Alamat Penyerah :</label>
      ${actionBean.penyerahAlamat1}
    </p>
    <p>
      <label>&nbsp;</label>
      ${actionBean.penyerahAlamat2}
    </p>
    <p>
      <label>&nbsp;</label>
      ${actionBean.penyerahAlamat3}
    </p>
    <p>
      <label>Bandar :</label>
      ${actionBean.penyerahAlamat4}
    </p>
    <p>
      <label>Poskod :</label>
      ${actionBean.penyerahPoskod}
    </p>
    <p>
      <label>Negeri :</label>
      ${actionBean.penyerahNegeriNama}
    </p>
    <br/>
  </fieldset>
  <br/>


  <fieldset class="aras1">
    <legend>Maklumat Urusan</legend>

    <display:table name="${actionBean.senaraiTransaksi}" id="row" class="tablecloth" style="width:95%;">
      <display:column title="Bil">
        ${row_rowNum}
      </display:column>
      <display:column title="Urusan" group="1" >
        <b>${row.utkUrusan.namaUrusan}</b>
      </display:column>
      <display:column title="Hakmilik Terlibat" group="2">
        <c:forTokens items="${row.senaraiHakmilik}" delims="," var="item">
          ${item}<br/>
        </c:forTokens>
      </display:column>

    </display:table>
    <br/>

  </fieldset>

  <br/>



  <p><label>&nbsp;</label>
    <c:if test="${actionBean.urusan ne 'HKTHK' && actionBean.urusan ne 'HSTHK'}">
      <s:submit name="Step5" value="Kembali" class="btn" />
    </c:if>
    <c:if test="${actionBean.urusan eq 'HKTHK' || actionBean.urusan eq 'HSTHK'}">
        <s:submit name="Step3a" value="Kembali" class="btn" />
    </c:if>    
    <s:submit name="CancelAll" value="Batal" class="btn" />                    
    <s:submit name="Step6" value="Selesai" class="btn" onclick="return validate(this.form);"/>
  </p>

</s:form>