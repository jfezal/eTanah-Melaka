<%-- 
    Document   : tanah_rizab_add
    Created on : Nov 9, 2010, 12:14:02 PM
    Author     : nursyazwani
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">


    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function save() {
        self.opener.refreshPageTanahRizab();
        self.close();
    }

    function validation() {
        if ($("#rizab").val() == "") {
            alert('Sila pilih " Jenis Rizab " terlebih dahulu.');
            $("#rizab").focus();
            return true;
        }
        if ($("#cawangan").val() == "") {
            alert('Sila pilih " Kod Caw " terlebih dahulu.');
            $("#cawangan").focus();
            return true;
        }
        if ($("#daerah").val() == "") {
            alert('Sila pilih " Daerah " terlebih dahulu.');
            $("#daerah").focus();
            return true;
        }
        if ($("#bandarPekanMukim").val() == "") {
            alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
            $("#bandarPekanMukim").focus();
            return true;
        }
        if ($("#nolot").val() == "") {
            alert('Sila pilih " No. PT/Lot " terlebih dahulu.');
            $("#nolot").focus();
            return true;
        }
        if ($("#nowarta").val() == "") {
            alert('Sila masukkan " No Warta " terlebih dahulu.');
            $("#nowarta").focus();
            return true;
        }
    <%--if($("#catatan").val() == ""){
        alert('Sila masukkan " Catatan " terlebih dahulu.');
        $("#catatan").focus();
        return true;
    }--%>

        }

        function save1(event, f) {
            if (validation()) {

            }
            else {
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url, q,
                        function(data) {
                            $('#page_div', opener.document).html(data);
//                            self.opener.refreshPageTanahRizab();
                            self.close();
                        }, 'html');
            }
        }
        $(document).ready(function() {
            $("#close").click(function() {
                setTimeout(function() {
                    self.close();
                }, 100);
            });
        });



</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

    <s:form beanclass="etanah.view.stripes.common.laporan.pelan.java.LaporanPelukisPelanNewActionBean">
        <fieldset class="aras1">
            <legend>
                Tanah Rizab
            </legend>
            <p>
                <label>Jenis Rizab :</label>
                <s:select name="rizab.kod" id="rizab" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label for="nowarta">No. Warta :</label>
                <s:text name="noWarta" size="20" id="nowarta"/>
            </p>
            <p>
                <label for="tarikhwarta">Tarikh Warta :</label>
                <s:text name="tarikhWarta" id="datepicker" class="datepicker" size="12" />
            </p>
            <p>
                <label for="pelanWarta">No Pelan Warta :</label>
                <s:text name="pelanWarta" size="20" id="pelanWarta"/>
            </p>
            <p>
                <label for="catatan">Catatan :</label>
                <s:textarea name="catatan" rows="5" cols="50" id="catatan"/>
            </p>
            <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.idMohonHakmilik}"/>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanTanahRizab" value="Simpan" class="btn" onclick="save1(this.name, this.form,'${actionBean.idMohonHakmilik}');" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


    </s:form>
</div>