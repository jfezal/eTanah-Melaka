<%-- 
    Document   : sedia_b2_pelan
    Created on : Aug 15, 2017, 2:46:12 AM
    Author     : john
--%>


<%@ page import="java.io.*,etanah.Configuration" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<link type="text/css" href="<%= request.getContextPath()%>/pub/select/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="<%= request.getContextPath()%>/pub/select/themes/base/jquery.ui.theme.css" rel="stylesheet" />
<link type="text/css" href="<%= request.getContextPath()%>/pub/select/themes/base/jquery.ui.selectmenu.css" rel="stylesheet" />
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.selectmenu.js"></script>
<style type="text/css">
    /* demo styles */
    body {font-size: 62.5%; font-family:"Verdana",sans-serif; }
    fieldset { border:0; }
    label,select,.ui-select-menu { float: left; margin-right: 10px; }
    select { width: 200px; }
    .wrap ul.ui-selectmenu-menu-popup li a { font-weight: bold; }
</style>
<script type="text/javascript">
    function janas(idSkimStrata) {

        var url = "${pageContext.request.contextPath}/strata/jana_cukai_petak?janaCukai&idSkimStrata=" + idSkimStrata;
        $.post(url,
                function(data) {
                    alert('s');
                }, 'html');

    }
    function selectAllHakmilik(a) {
        alert("selectall");
        //alert(a);
        for (i = 0; i < 100; i++) {
            var c = document.getElementById("checkboxhakmilikInduk" + i);
            //alert(c);
            if (c == null)
                break;
            c.checked = a.checked;
        }
    }

    function kemaskiniHakmilik() {
        if (confirm('Adakah anda pasti untuk kemaskini Hakmilik ini')) {
            var id = $('#hiddenIdHakmilik').val();
            var param = '';
            $('.allhakmilik').each(function(index) {
                var a = $('#checkboxhakmilikInduk' + index).is(":checked");
                if (a) {
                    param = param + '&senaraiSkim=' + $('#checkboxhakmilikInduk' + index).val();
                }
            });

            if (param == '') {
                alert('Sila Pilih hakmilik terlebih dahulu.');
                return;
            }

            var url = '${pageContext.request.contextPath}/strata/jana_cukai_petak?janaBerkumpulan' + param + '&idHakmilik=' + id;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        $.unblockUI();
                    }, 'html');
        }
    }
</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.strata.cukaipetak.JanaCukaiPetakActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>  

    <div class="subtitle">
         <fieldset class="aras1">
   
        <legend>Maklumat Asas</legend>
        <%--${actionBean.pengguna.perananUtama.kod}--%>
        
         <p><label>Id Hakmilik :</label>
          ${actionBean.hakmilik.idHakmilik}&nbsp;
          
        </p>
       
        <p><label>Daerah :</label>
          ${actionBean.hakmilik.daerah.nama}&nbsp;
          <s:text name="report_p_id_hakmilik" value="${actionBean.hakmilik.idHakmilik}" id="id_hakmilik" style="display:none"/>
        </p>
        <p>
          <label>Bandar/Pekan/Mukim :</label>
          ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
        </p>
        <p>
          <label>Seksyen :</label>
          <c:if test="${actionBean.hakmilik.seksyen.kod ne null}">
            ${actionBean.hakmilik.seksyen.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.seksyen.kod eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Tempat :</label>
          <c:if test="${actionBean.hakmilik.lokasi ne null}">
            ${actionBean.hakmilik.lokasi}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.lokasi eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Jenis Hakmilik :</label>
          ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
        </p>
        <p>
          <label>Jenis Kategori Tanah :</label>
          ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
        </p>
        <p>
          <label> Kegunaan Tanah :</label>
          ${actionBean.hakmilik.kegunaanTanah.nama}&nbsp;
        </p>
        <p>
          <label>Kod Lot :</label>
          ${actionBean.hakmilik.lot.nama}&nbsp;
        </p>
        <p>
          <label>
              <c:choose>
                  <c:when test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                          || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">No Lot Stratum</c:when>
                  <c:otherwise>No Lot/PT </c:otherwise>
              </c:choose>
              :
          </label>
          ${actionBean.hakmilik.noLot}&nbsp;
        </p>
        <p>
          <label>Tarikh Daftar :</label>
          <fmt:formatDate type="date" pattern="dd/MM/yyyy"
                          value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;
        </p>
        <p>
          <label>Tanah Rezab :</label>
          <c:if test="${actionBean.hakmilik.rizab ne null}">
            ${actionBean.hakmilik.rizab.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.rizab eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Pihak Berkuasa Tempatan :</label>
          <c:if test="${actionBean.hakmilik.pbt ne null}">
            ${actionBean.hakmilik.pbt.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.pbt eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Taraf Pegangan :</label>
          <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
            Pajakan
          </c:if>
          <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
            Selama - lama
          </c:if>
          &nbsp;
        </p>
        <p>
          <label>Tempoh :</label>
          <c:if test="${actionBean.hakmilik.tempohPegangan ne null && actionBean.hakmilik.tempohPegangan ne 0 }">
            ${actionBean.hakmilik.tempohPegangan} Tahun&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">
            -
          </c:if>
          <c:if test="${actionBean.hakmilik.tempohPegangan eq 0}">
            -
          </c:if>
        </p>
        <p>
          <label>Tarikh Luput :</label>
          <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">
            Tiada
          </c:if>
          <fmt:formatDate type="date" pattern="dd/MM/yyyy"
                          value="${actionBean.hakmilik.tarikhLuput}"/>&nbsp;
        </p>
        <p>
          <label for="noPu">No Permohonan Ukur :</label>
          <c:if test="${actionBean.hakmilik.noPu ne null}">
            ${actionBean.hakmilik.noPu}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.noPu eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label >Syarat Nyata :</label>
          ${actionBean.hakmilik.syaratNyata.kod}&nbsp;
        </p>
        <p>
          <label>&nbsp;</label>
          <s:textarea name="syaratNyata" rows="5" cols="60" id="syarat" readonly="true">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
          </p>
          <p>
            <label >Sekatan :</label>
          ${actionBean.hakmilik.sekatanKepentingan.kod}&nbsp;
        </p>
        <p>
          <label>&nbsp;</label>
          <s:textarea name="sekatan" rows="5" cols="60" id="sekatan" readonly="true">${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
          </p>
          <p>
            <label>No Pelan Piawai :</label>
          <c:if test="${actionBean.hakmilik.noLitho ne null}">
            ${actionBean.hakmilik.noLitho}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.noLitho eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>
                <c:choose>
                    <c:when test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                                    || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">No Pelan Stratum Diperakui</c:when>
                    <c:otherwise>No Pelan Diperakui</c:otherwise>
                </c:choose>
               :
          </label>
          <c:if test="${actionBean.hakmilik.noPelan ne null}">
            ${actionBean.hakmilik.noPelan}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.noPelan eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label >
              <c:choose>
                    <c:when test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                                    || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">Isipadu Lot Stratum</c:when>
                  <c:otherwise>Keluasan</c:otherwise>
              </c:choose>                
              :</label>
          <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
        </p>
        <c:if test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                      || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">
            <p>
                <label>Kedalaman :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.kedalamanTanah}"/>&nbsp;
                <c:if test="${actionBean.hakmilik.kedalamanTanahUOM ne null}">
                    ${actionBean.hakmilik.kedalamanTanahUOM.nama}
                </c:if>
            </p>
        </c:if>
        <p>
          <label >Cukai Asal (RM)  :</label>

          <fmt:formatNumber value="${actionBean.hakmilik.cukai}" pattern="0.00"/>&nbsp;
        </p>
        <p>
          <label >Cukai Tanah Tahunan (RM)  :</label>
          <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/>&nbsp;
        </p>
        <p>
          <label>No Versi DHKE :</label>
          ${actionBean.hakmilik.noVersiDhke}&nbsp;
        </p>
        <p>
          <label>No Versi DHDE :</label>
          ${actionBean.hakmilik.noVersiDhde}&nbsp;
        </p>

        <p>
          <label>Tarikh Daftar Asal :</label>
          <c:if test="${actionBean.hakmilik.tarikhDaftarAsal ne ''}">
            <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftarAsal}"/>&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.tarikhDaftarAsal eq ''}">
            - &nbsp;
          </c:if>
        </p>
        <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B'}" >
          <p>
            <label>Tarikh Batal :</label>
            <%--${actionBean.hakmilik.tarikhBatal}--%>
            <fmt:formatDate type="date" pattern="dd/MM/yyyy"
                            value="${actionBean.hakmilik.tarikhBatal}"/>&nbsp;
          </p>
        </c:if>
        <p>
          <label>No Rujukan Fail :</label>
          ${actionBean.hakmilik.noFail}&nbsp;
        </p>
        <br>
      </fieldset>
    </div>
</s:form>