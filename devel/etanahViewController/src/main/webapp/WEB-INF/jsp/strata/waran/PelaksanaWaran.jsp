<%--
    Document   : PelaksanaWaran.jsp
    Created on : Apr 7, 2011, 12:24:04 AM
    Author     : zadhirul.farihim
    Modified By: Murali
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
            //   refreshPage();
        },'html');
    }
    function hapus(idPelaksana){

        var url = '${pageContext.request.contextPath}/strata/pelaksana?hapusPelaksanaWaran&idPelaksana='+idPelaksana;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function tambahPelaksanaq(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/pelaksana?tambahPelaksana';
        window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
    }
    function validate(){
        var nama = document.getElementById("nama").value;
        var jenisKP = document.getElementById("jenisKP").value;
        var noPengenalan = document.getElementById("noPengenalan").value;
        var alamat1 = document.getElementById("alamat1").value;
        var poskod = document.getElementById("poskod").value;
        var negeri = document.getElementById("negeri").value;


        if ((nama == ""))
        {
            alert('Sila masukkan nama');
            document.getElementById("nama").focus();
            return false;
        }
        else if ((jenisKP == ""))
        {
            alert('Sila pilih jenis pengenalan');
            document.getElementById("jenisKP").focus();
            return false;
        }
        else if ((noPengenalan == ""))
        {
            alert('Sila masukkan no pengenalan');
            document.getElementById("noPengenalan").focus();
            return false;
        }
        else if ((alamat1 == ""))
        {
            alert('Sila masukkan alamat');
            document.getElementById("alamat1").focus();
            return false;
        }
        else if ((poskod == ""))
        {
            alert('Sila masukkan poskod');
            document.getElementById("poskod").focus();
            return false;
        }
         else if ((negeri == ""))
        {
            alert('Sila pilih negeri');
            document.getElementById("negeri").focus();
            return false;
        }          
        else{
            return true;
        }
    }
    function save(event, f){
        var name = document.getElementById("nama").value;
        if((name == "")) {

        }else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');
        }
    }
    function save1(event, f){

        //        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        //        $.get(url,q,
        //        function(data){
        //            $('#page_div').html(data);
        //
        //        },'html');
        $.ajax({
            type:"POST",
            url : url,
            success : function(data) {
                $('#page_div').html(data);
            }
        });
    }
    function save2(event, f){
        var dt = document.getElementById("tarikhPelantikan").value;
        if((dt == "")) {
            alert("Sila masukkan tarikh");
        }else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');
        }
    }

    function kemaskiniPelaksan(val){

        var url = '${pageContext.request.contextPath}/strata/pelaksana?showEdit&id='+val;

        window.open(url, "etanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=350");

    }
    function tutup1(){
        var url = '${pageContext.request.contextPath}/strata/pelaksana';
        $.ajax({
            type:"POST",
            url : url,
            success : function(data) {
                $('#page_div').html(data);
            }
        });
    }

    function dpicker1(){
    <%--alert("date1");--%>
    <%--$("#tarikhPelantikan").datepicker();--%>
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        }

    <%--function dpicker2(){
          var flag = $("#flag").val();
          alert(flag);
        if(flag == 'true'){
            alert("d:"+$("#tarikhPelantikan").datepicker().val());
            var d = new Date($("#tarikhPelantikan").datepicker().val());
            var curr_date = d.getDate();
            var curr_month = d.getMonth() + 1; //months are zero based
            var curr_year = d.getFullYear();
            alert(curr_date + "/" + curr_month + "/" + curr_year);
            $("#tarikhPelantikan").val(curr_date + "/" + curr_month + "/" + curr_year);           
        }
      }--%>
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%-- original <s:form name="form1" beanclass="etanah.view.strata.PelaksanaWaranActionBean">--%>
<s:form beanclass="etanah.view.strata.PelaksanaWaranActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="flag" id="flag" value="false"/>

    <div class="subtitle">
        <fieldset class="aras1">

            <c:if test="${tambah}">
                <legend>Tambah Pelaksana Waran</legend>

                <p>
                    <label title="Nama" >Nama :</label><s:text name="nama" id="nama" size="50" class="normal_text"/>
                </p>
                <p>
                    <label>Jenis Pengenalan :</label>
                    <s:select id="jenisKP" name="jenisKP" >
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label title="No Pengenalan" >No Pengenalan :</label><s:text name="noPengenalan" id="noPengenalan"  maxlength="12" onkeyup="validateNumber(this,this.value);"/> <font size="1" color="green">cth: 851212046262</font>
                </p>

                <p>
                    <label title="Alamat">Alamat :</label><s:text name="alamat1" id="alamat1" size="50" class="normal_text"/>
                </p>
                <p>
                    <label title="Alamat">&nbsp;</label><s:text name="alamat2" id="alamat2" size="50" class="normal_text"/>
                </p>
                <p>
                    <label title="Alamat">&nbsp;</label><s:text name="alamat3" id="alamat3" size="50" class="normal_text"/>
                </p>
                <p>
                    <label title="Alamat">&nbsp;</label><s:text name="alamat4" id="alamat4" size="50" class="normal_text"/>
                </p>
                <p>
                    <label title="Poskod">Poskod :</label><s:text formatType="number" maxlength="5" name="poskod" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Negeri :</label>
                    <s:select id="negeri" name="negeri" >
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label title="Pekerjaan">Pekerjaan :</label><s:text name="kerja" class="normal_text"/>

                </p>
                <p >
                    <label>&nbsp;</label> <s:button name="simpan" value="Simpan" class="btn" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}"/>
                    <s:button name="tutup" value="Tutup" class="btn" onclick="tutup1();"/>
                </p>
                <br>
                <br>
            </c:if>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Pelaksana Waran</legend>
            <c:if test="${!actionBean.readOnly}">
                <s:button name="tambahPelaksana" value="Tambah" onclick="save1(this.name, this.form);" class="btn"/>
            </c:if>
            <display:table  style="width:90%" class="tablecloth" name="${actionBean.listPelaksana}" id="line">
                <display:column title="Bil" style="width:3%;">${line_rowNum}</display:column>
                <display:column   title="Nama" style="width:30%;" property="nama"/>
                <display:column title="No Kad Pengenalan" style="width:10%;" property="noPengenalan"/>
                <display:column title="Pekerjaan" property="kerja"/>
                <display:column title="Alamat" style="width:40%;">
                    <c:if test="${line.alamat1 ne null}">
                        ${line.alamat1},
                    </c:if>
                    <c:if test="${line.alamat2 ne null}">
                        ${line.alamat2},
                    </c:if>
                    <c:if test="${line.alamat3 ne null}">
                        ${line.alamat3},
                    </c:if>
                    <c:if test="${line.alamat4 ne null}">
                        ${line.alamat4},
                    </c:if>
                    <c:if test="${line.poskod ne null}">
                        ${line.poskod},
                    </c:if>
                    <c:if test="${line.negeri ne null}">
                        ${line.negeri.nama}
                    </c:if>
                </display:column>
                <c:if test="${!actionBean.readOnly}">
                    <display:column title="Hapus"><div align="center"><img src="${pageContext.request.contextPath}/pub/images/not_ok.gif" alt="Hapus Data" width="15" height="15" title="Hapus Data" onclick="hapus('${line.idPelaksanaWaran}');return false;" /></div></display:column>
                    <display:column title="Kemaskini"><div align="center"><img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id="" title="Klik Untuk Kemaskini" onclick="kemaskiniPelaksan('${line.idPelaksanaWaran}')" onmouseover="this.style.cursor='pointer';"></div></display:column>
                    </c:if>
                </display:table>
        </fieldset>


    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tarikh Perlantikan</legend>
            <c:if test="${!actionBean.readOnly}">
                <p>
                    <%--<label> Tarikh Perlantikan :</label><s:text name="tarikhPelantikan" id="tarikhPelantikan" readonly="${actionBean.readOnly}" formatPattern="dd/MM/yyyy" class="datepicker" size="30" onmouseover="dpicker1()" onclick="dpicker1();" onchange="dpicker2();"/>--%>
                    <label> Tarikh Perlantikan :</label><s:text name="tarikhPelantikan" id="tarikhPelantikan" readonly="${actionBean.readOnly}" formatPattern="dd/MM/yyyy" class="datepicker" size="30" onmouseover="dpicker1()" onclick="dpicker1();"/>
                </p>
            </c:if>
            <c:if test="${actionBean.readOnly}">
                <p>
                    <label> Tarikh Perlantikan :</label><s:text name="tarikhPelantikan" id="tarikhPelantikan1" readonly="${actionBean.readOnly}" formatPattern="dd/MM/yyyy"  size="30"/>
                </p>
            </c:if>
            <c:if test="${!actionBean.readOnly}">
                <p>
                    <label> &nbsp;</label> <s:button name="saveTarikhPelantikan" value="Simpan" class="btn" onclick="save2(this.name, this.form)"/>
                </p>
            </c:if>
        </fieldset>

    </div>


</s:form>
